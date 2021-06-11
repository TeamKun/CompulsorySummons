package net.kunmc.lab.compulsorysummons.events

import net.kunmc.lab.compulsorysummons.Manager
import net.kunmc.lab.compulsorysummons.util.AliveSizeCheck

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener: Listener {
    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        if(Manager.brEnabled) {
            if(Manager.alivePlayers.contains(e.player)) {
                Manager.alivePlayers.remove(e.player)
            }
            AliveSizeCheck().check()
        }
    }
}