package net.kunmc.lab.compulsorysummons.commands

import net.kunmc.lab.compulsorysummons.CompulsorySummonsPlugin
import net.kunmc.lab.compulsorysummons.Const
import net.kunmc.lab.compulsorysummons.Manager

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import java.lang.Exception

class ConfigCommand {
    // config.ymlを読み込む
    private val config = CompulsorySummonsPlugin.plugin.config

    fun execute(sender: CommandSender, args: Array<out String>) {
        when(args[0]) {
            Const.COMMAND_GIVE -> {
                give(sender, args)
            }
            Const.COMMAND_REMOVE -> {
                remove(sender, args)
            }
            Const.COMMAND_SET_TP -> {
                setTP(sender, args)
            }
        }
    }

    // 効果を付与する
    private fun give(sender: CommandSender, args: Array<out String>) {
        // 引数の数が２個以外なら
        if(args.size != 2) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 引数の数が不正です")
            return
        }

        // サバイバルモードが実行されていない場合
        if(!Manager.svEnabled) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 現在サバイバルモードが実行されていません")
            return
        }
        // BRモードが実行されている場合
        if(Manager.brEnabled) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: バトルロイヤルモード中です、効果の付与ができません")
            return
        }

        // @aが入力された場合、一度リストを空にする
        if(args[1] == "@a") {
            Manager.enchantingPlayers.clear()
        }

        // Entityのリストを宣言
        val entities: MutableList<Entity>

        try {
            entities = Bukkit.selectEntities(sender, args[1])
        }
        // 存在しないプレイヤー名が入力されたら
        catch(e: Exception) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 存在しないプレイヤー名が入力されました")
            return
        }
        // サーバに接続していないプレイヤー名が入力されたら
        if(entities.isEmpty()) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: サーバに接続していないプレイヤー名が入力されました")
            return
        }

        entities.forEach {
            // すでに付与されているプレイヤー名が入力されたら
            if(Manager.enchantingPlayers.contains(it.uniqueId)) {
                sender.sendMessage("" + ChatColor.RED + "ERROR: すでに付与されているプレイヤー名が入力されました")
                return
            }
            Manager.enchantingPlayers.add(it.uniqueId)
        }
        Manager.enchantingPlayers = Manager.enchantingPlayers.distinct().toMutableList()

        sender.sendMessage("" + ChatColor.GREEN + "入力されたプレイヤーに効果を付与しました")
    }

    // 効果を剥奪する
    private fun remove(sender: CommandSender, args: Array<out String>) {
        // 引数の数が２個以外なら
        if(args.size != 2) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 引数の数が不正です")
            return
        }

        // BRモードが実行されている場合
        if(Manager.brEnabled) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: バトルロイヤルモード中です、効果の剥奪ができません")
            return
        }

        // @aが入力された場合、リストを空にする
        if(args[1] == "@a") {
            Manager.enchantingPlayers.clear()
            sender.sendMessage("" + ChatColor.GREEN + "全プレイヤーから効果を剝奪しました")
            return
        }

        // Entityのリストを宣言
        val entities: MutableList<Entity>

        try {
            entities = Bukkit.selectEntities(sender, args[1])
        }
        // 存在しないプレイヤー名が入力されたら
        catch(e: Exception) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 存在しないプレイヤー名が入力されました")
            return
        }
        // サーバに接続していないプレイヤー名が入力されたら
        if(entities.isEmpty()) {
            // サーバに接続していないがリストにIDがあった場合
            if(Manager.enchantingPlayers.contains(Bukkit.getPlayerUniqueId(args[1]))) {
                val idx: Int = Manager.enchantingPlayers.indexOf(Bukkit.getPlayerUniqueId(args[1]))
                Manager.enchantingPlayers.removeAt(idx)
                sender.sendMessage("" + ChatColor.GREEN + "入力されたプレイヤーから効果を剝奪しました")
                return
            }
            sender.sendMessage("" + ChatColor.RED + "ERROR: サーバに接続していないプレイヤー名が入力されました")
            return
        }

        entities.forEach {
            val idx: Int = Manager.enchantingPlayers.indexOf(it.uniqueId)
            // 格納されていないプレイヤーの名前が入力されたら
            if(idx == -1) {
                sender.sendMessage("" + ChatColor.RED + "ERROR: 付与されていないプレイヤー名が入力されました")
                return
            }
            Manager.enchantingPlayers.removeAt(idx)
            sender.sendMessage("" + ChatColor.GREEN + "入力されたプレイヤーから効果を剝奪しました")
        }
    }

    // TP人数の設定
    private fun setTP(sender: CommandSender, args: Array<out String>) {
        // 引数の数が２個以外なら
        if(args.size != 2) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 引数の数が不正です")
            return
        }

        try {
            val num: Int = Integer.parseInt(args[1])
            if(Bukkit.getOnlinePlayers().size - 1 < num) {
                sender.sendMessage("" + ChatColor.RED + "ERROR: 強制召喚人数はサーバに接続しているプレイヤー数未満にしてください")
                return
            }

            config.set("tp", num)
            CompulsorySummonsPlugin.plugin.saveConfig()
            sender.sendMessage("" + ChatColor.GREEN + "TP人数が${config.get("tp")}人に変更されました")
        }
        catch(e: NumberFormatException) {
            sender.sendMessage("" + ChatColor.RED + "ERROR: 第２引数には整数値を入力してください")
            return
        }
    }
}