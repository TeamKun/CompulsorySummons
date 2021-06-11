package net.kunmc.lab.compulsorysummons

import org.bukkit.entity.Player
import java.util.*

class Manager {
    companion object {
        var brEnabled: Boolean = false
        var svEnabled: Boolean = false

        // 付与しているプレイヤーのリスト
        var enchantingPlayers = mutableListOf<UUID>()

        // BRモードで使用する生き残っているプレイヤーのリスト
        val alivePlayers = mutableListOf<Player>()
    }
}