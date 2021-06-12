package net.kunmc.lab.compulsorysummons.commands

import net.kunmc.lab.compulsorysummons.CompulsorySummonsPlugin
import net.kunmc.lab.compulsorysummons.Manager
import net.kunmc.lab.compulsorysummons.Const
import net.kunmc.lab.compulsorysummons.util.AliveSizeCheck
import net.kunmc.lab.compulsorysummons.util.Timer
import net.kunmc.lab.compulsorysummons.util.Util
import org.bukkit.Bukkit

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

class ModeCommand {
    // config.ymlを読み込む
    private val config = CompulsorySummonsPlugin.plugin.config

    fun execute(sender: CommandSender, subCommand: String) {
        when(subCommand) {
            // バトルロイヤルモード
            Const.COMMAND_BATTLE_ROYAL_MODE -> {
                if(Manager.brEnabled) {
                    sender.sendMessage("" + ChatColor.RED + "ERROR: 現在実行中です")
                    return
                }
                Manager.enchantingPlayers.clear()
                Manager.brEnabled = true
                Timer().countDownTimer(10, sender)
            }
            // サバイバルモード
            Const.COMMAND_SURVIVAL_MODE -> {
                survivalMode(sender)
            }
            // 強制終了
            Const.COMMAND_STOP -> {
                stop(sender)
            }
        }
    }

    // バトルロイヤルモード
    fun battleRoyalMode(sender: CommandSender) {
        Bukkit.getOnlinePlayers().forEach {
            Manager.enchantingPlayers.add(it.uniqueId)
            Manager.alivePlayers.add(it)
        }

        // もし開始したときに１人だけだった場合
        if(Manager.alivePlayers.size == 1) {
            AliveSizeCheck().check()
            return
        }

        sender.sendMessage(
            "" + ChatColor.GOLD + "バトルロイヤルモードが開始されました\n" +
            "現在の強制召喚人数は${config.get("tp")}人に設定されています"
        )

        Util().sendTitleAll(
            "ゲームが開始されました",
            "プラグインを利用して１位を目指そう！"
        )
    }

    // サバイバルモード
    private fun survivalMode(sender: CommandSender) {
        if(Manager.svEnabled) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 現在実行中です")
            return
        }

        Manager.svEnabled = true
        Manager.enchantingPlayers.clear()

        sender.sendMessage(
            "" + ChatColor.GOLD + "サバイバルモードが開始されました\n" +
            "現在の強制召喚人数は${config.get("tp")}人に設定されています"
        )
        sender.sendMessage(
            "" + ChatColor.RED + "[注意]全プレイヤーから効果を剥奪しました\n" +
            "" + ChatColor.RED + "/summons give <プレイヤー名> を使用して効果を付与してください"
        )
    }

    // 強制終了
    private fun stop(sender: CommandSender) {
        if(Manager.brEnabled) {
            Manager.brEnabled = false
            Manager.alivePlayers.clear()
            Util().sendMessageAll("ゲームが強制終了されました", ChatColor.GREEN)
        }
        else if(Manager.svEnabled) {
            Manager.svEnabled = false
            sender.sendMessage("" + ChatColor.GREEN + "サバイバルモードを終了しました")
            sender.sendMessage("" + ChatColor.RED + "全プレイヤーから効果を剥奪しました")
        }
        else {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 実行中のゲームモードがありません")
            return
        }
        Manager.enchantingPlayers.clear()
    }
}