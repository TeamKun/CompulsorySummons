package net.kunmc.lab.compulsorysummons.events

import net.kunmc.lab.compulsorysummons.Manager

import net.kunmc.lab.compulsorysummons.CompulsorySummonsPlugin
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakListener: Listener {
    // config.ymlを読み込む
    private val config = CompulsorySummonsPlugin.plugin.config

    @EventHandler
    fun onBreak(e: BlockBreakEvent) {
        // いずれかのモードが実行中の場合のみ
        if(Manager.svEnabled || Manager.brEnabled) {
            // 効果が付与されているプレイヤーのみ
            if(Manager.enchantingPlayers.contains(e.player.uniqueId)) {
                // 必要な変数を宣言
                val p: Player = e.player
                val op = mutableListOf<Player>()

                // 自分以外のプレイヤーをリストに格納
                Bukkit.getOnlinePlayers().forEach {
                    if(it != p && it.gameMode != GameMode.SPECTATOR)
                        op.add(it)
                }

                // ランダムにするためshuffleする
                op.shuffle()

                // 指定人数分ブロックを掘ったプレイヤーの所にTPさせる
                for(i in 1..config.getInt("tp")) {
                    if(op.size < i)
                        break
                    op[i-1].teleport(p.location)
                }
            }
        }
    }
}