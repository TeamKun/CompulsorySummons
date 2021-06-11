package net.kunmc.lab.compulsorysummons.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor

class Util {
    fun sendTitleAll(title: String, subTitle: String) {
        Bukkit.getOnlinePlayers().forEach {
            it.sendTitle(
                "" + ChatColor.GOLD + title,
                "" + subTitle, 10, 20 * 6, 10
            )
        }
    }

    fun sendMessageAll(message: String, color: ChatColor) {
        Bukkit.getOnlinePlayers().forEach {
            it.sendMessage("" + color + message)
        }
    }
}