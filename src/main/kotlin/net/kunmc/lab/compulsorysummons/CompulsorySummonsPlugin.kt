package net.kunmc.lab.compulsorysummons

import net.kunmc.lab.compulsorysummons.commands.CommandListener
import net.kunmc.lab.compulsorysummons.commands.TabCompleter
import net.kunmc.lab.compulsorysummons.events.*

import org.bukkit.plugin.java.JavaPlugin

class CompulsorySummonsPlugin: JavaPlugin() {
    companion object {
        lateinit var plugin: JavaPlugin
    }

    override fun onEnable() {
        plugin = this
        // config.ymlがない場合は生成する
        saveDefaultConfig()

        getCommand("compulsorySummons")?.setExecutor(CommandListener())
        getCommand("compulsorySummons")?.setTabCompleter(TabCompleter())
        server.pluginManager.registerEvents(BlockBreakListener(), this)
        server.pluginManager.registerEvents(PlayerDeathListener(), this)
        server.pluginManager.registerEvents(PlayerJoinListener(), this)
        server.pluginManager.registerEvents(PlayerQuitListener(), this)
    }
}