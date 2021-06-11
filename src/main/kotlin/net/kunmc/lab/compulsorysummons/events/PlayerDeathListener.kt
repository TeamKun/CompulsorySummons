package net.kunmc.lab.compulsorysummons.events

import net.kunmc.lab.compulsorysummons.Manager
import net.kunmc.lab.compulsorysummons.util.AliveSizeCheck

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeathListener: Listener{
    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        // バトルロイヤルモードが有効か
        if(Manager.brEnabled) {
            // 死亡したプレイヤーをスペクテイターモードに変数する
            if(e.entity.gameMode != GameMode.SPECTATOR) {
                val player = e.entity
                player.gameMode = GameMode.SPECTATOR
            }

            val idx: Int = Manager.alivePlayers.indexOf(e.entity)
            if(idx == -1) {
                return
            }

            // 生存者リストから死亡したプレイヤーを削除する
            Manager.alivePlayers.removeAt(idx)
            AliveSizeCheck().check()
        }
    }
}