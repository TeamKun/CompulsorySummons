package net.kunmc.lab.compulsorysummons.commands

import net.kunmc.lab.compulsorysummons.CompulsorySummonsPlugin
import net.kunmc.lab.compulsorysummons.Const
import net.kunmc.lab.compulsorysummons.Manager
import org.bukkit.Bukkit
import org.bukkit.ChatColor

import org.bukkit.command.CommandSender

class SettingsCommand {
    // config.ymlを読み込む
    private var config = CompulsorySummonsPlugin.plugin.config

    fun execute(sender: CommandSender, args: Array<out String>) {
        if(args.size != 2) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 引数の数が不正です")
            return
        }

        when(args[1]) {
            Const.COMMAND_ENABLE_PLAYER -> {
                enablePlayer(sender)
            }
            Const.COMMAND_SHOW_TP -> {
                showTP(sender)
            }
            Const.COMMAND_RELOAD -> {
                reload(sender)
            }
            else -> {
                sender.sendMessage("" + ChatColor.RED + "ERROR: 不明な引数です")
            }
        }
    }

    // 効果を付与されているプレイヤーを確認
    private fun enablePlayer(sender: CommandSender) {
        var nameList = ""
        var cnt = 0
        Manager.enchantingPlayers.forEach {
            nameList += Bukkit.getOfflinePlayer(it).name + ", "
            cnt++
        }

        sender.sendMessage("" + ChatColor.GREEN + "現在${cnt}人のプレイヤーに効果が付与されています")
        sender.sendMessage("" + nameList)
    }

    // 強制召喚する人数を確認
    private fun showTP(sender: CommandSender) {
        sender.sendMessage("" + ChatColor.GREEN + "現在の強制召喚の人数は${config.get("tp", 1)}人です")
    }

    // コンフィグの再読み込み
    private fun reload(sender: CommandSender) {
        CompulsorySummonsPlugin.plugin.reloadConfig()
        config = CompulsorySummonsPlugin.plugin.config
        sender.sendMessage("" + ChatColor.GOLD + "config.ymlが再読み込みされました")
        sender.sendMessage("" + ChatColor.GREEN + "現在の強制召喚の人数は${config.get("tp", 1)}人です")
    }
}