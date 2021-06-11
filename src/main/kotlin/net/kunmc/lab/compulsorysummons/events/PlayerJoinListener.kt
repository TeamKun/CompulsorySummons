package net.kunmc.lab.compulsorysummons.events

import net.kunmc.lab.compulsorysummons.Manager

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener: Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        if(Manager.brEnabled) {
            val p: Player = e.player
            // 途中ログインしたプレイヤーをスペクテイターモードに変数する
            if(p.gameMode != GameMode.SPECTATOR) {
                p.gameMode = GameMode.SPECTATOR
            }
        }
    }
}