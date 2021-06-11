package net.kunmc.lab.compulsorysummons.commands

import net.kunmc.lab.compulsorysummons.Const

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CommandListener: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        // 引数の数が０個だった場合
        if(args.isEmpty()) {
            sender.sendMessage("" + ChatColor.RED + "引数の数が不正です")
            return true
        }

        // 引数が渡された時の処理
        when(args[0]) {
            // ゲームモードに関するコマンド
            Const.COMMAND_BATTLE_ROYAL_MODE,
            Const.COMMAND_SURVIVAL_MODE,
            Const.COMMAND_STOP -> {
                ModeCommand().execute(sender, args[0])
            }

            // 設定に関するコマンド
            Const.COMMAND_GIVE,
            Const.COMMAND_REMOVE,
            Const.COMMAND_SET_TP -> {
                ConfigCommand().execute(sender, args)
            }

            // 設定値確認に関するコマンド
            Const.COMMAND_SETTINGS -> {
                SettingsCommand().execute(sender, args)
            }

            else -> {
                sender.sendMessage("" + ChatColor.RED + "ERROR: 不正な引数です")
            }
        }
        return true
    }
}