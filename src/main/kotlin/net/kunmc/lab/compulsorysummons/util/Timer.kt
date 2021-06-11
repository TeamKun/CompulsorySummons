package net.kunmc.lab.compulsorysummons.util

import net.kunmc.lab.compulsorysummons.CompulsorySummonsPlugin
import net.kunmc.lab.compulsorysummons.commands.ModeCommand

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.scheduler.BukkitRunnable

class Timer {
    fun countDownTimer(seconds: Int, sender: CommandSender) {
        var remainSeconds = seconds
        object: BukkitRunnable() {
            override fun run() {
                if(0 <= remainSeconds) {
                    sendTimer(remainSeconds)
                    remainSeconds--
                }
                else {
                    ModeCommand().battleRoyalMode(sender)
                    cancel()
                }
            }
        }.runTaskTimer(CompulsorySummonsPlugin.plugin, 0, 20)
    }

    private fun sendTimer(seconds: Int) {
        Bukkit.getOnlinePlayers().forEach { player ->
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("スタートまであと " + seconds + "秒 です"))
        }
    }
}