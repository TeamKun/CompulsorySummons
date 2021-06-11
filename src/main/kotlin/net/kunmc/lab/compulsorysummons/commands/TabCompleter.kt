package net.kunmc.lab.compulsorysummons.commands

import net.kunmc.lab.compulsorysummons.Manager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class TabCompleter: TabCompleter {
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        var result: MutableList<String> = mutableListOf()
        if(args.size == 1) {
            result.addAll(listOf("battleRoyalMode", "survivalMode", "stop", "give", "remove", "setTP", "settings"))
            result = result.filter {
                it.startsWith(args[0])
            }.toMutableList()
        }
        else if(args.size == 2 && (args[0] == "give" || args[0] == "remove")) {
            result.clear()
            result.addAll(listOf("@a"))
            for(p in Bukkit.getOnlinePlayers()) {
                if(args[0] == "remove" && !Manager.enchantingPlayers.contains(p.uniqueId)) {
                    continue
                }
                result.add(p.name)
            }
            result = result.filter {
                it.startsWith(args[1])
            }.toMutableList()
        }
        else if(args.size == 2 && args[0] == "setTP") {
            result.clear()
            result.add("<TP人数(整数)>")
        }
        else if(args.size == 2 && args[0] == "settings") {
            result.addAll(listOf("enablePlayer", "showTP", "reload"))
            result = result.filter {
                it.startsWith(args[1])
            }.toMutableList()
        }
        else {
            result.clear()
        }
        return result
    }
}