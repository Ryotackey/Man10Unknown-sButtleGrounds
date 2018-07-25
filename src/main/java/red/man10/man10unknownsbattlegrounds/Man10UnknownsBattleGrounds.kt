package red.man10.man10unknownsbattlegrounds

import org.bukkit.Location
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import red.man10.kotlin.CustomConfig

class Man10UnknownsBattleGrounds : JavaPlugin() {

    val arena = CustomConfig(this, "arenadata.yml")

    val arenamap = HashMap<String, ArenaInfo>()

    val prefix = "§l[§d§lM§f§lU§a§lB§e§lG§f§l]"

    override fun onEnable() {
        // Plugin startup logic

        arena.saveDefaultConfig()

        getCommand("mupg").executor = Commands(this)

        server.pluginManager.registerEvents(Event(this), this)

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    class ArenaInfo{

        val itemloc = mutableListOf<Location>()
        val spawnloc = mutableListOf<Location>()
        val itemmap = HashMap<Double, ItemStack>()

    }

}
