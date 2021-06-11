package net.kunmc.lab.compulsorysummons.util

import net.kunmc.lab.compulsorysummons.Manager

class AliveSizeCheck {
    fun check() {
        if(Manager.alivePlayers.size == 1) {
            Manager.enchantingPlayers.clear()
            Manager.brEnabled = false
            Util().sendTitleAll("ゲーム終了", "優勝者は${Manager.alivePlayers[0].name}さんです")
            Manager.alivePlayers.clear()
        }
    }
}