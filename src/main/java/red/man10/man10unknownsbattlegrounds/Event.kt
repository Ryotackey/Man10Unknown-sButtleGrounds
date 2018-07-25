package red.man10.man10unknownsbattlegrounds

import org.bukkit.Material
import org.bukkit.block.ShulkerBox
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.meta.BlockStateMeta

class Event(val plugin: Man10UnknownsBattleGrounds): Listener {

    @EventHandler
    fun onClick(e: InventoryClickEvent){

        val inv = e.clickedInventory

        val p = e.whoClicked as Player

        if (plugin.arenamap.containsKey(inv.name)){

            val item = e.currentItem

            if (item.itemMeta.displayName == "§l次のページ") {

                val pageitem = e.clickedInventory.getItem(49)
                val page = pageitem.itemMeta.displayName.toInt() + 1

                val gp = GuiProcess(plugin)
                p.openInventory(gp.allBookGuiCreate(p, page, inv.name))

            }

            if (item.itemMeta.displayName == "§l前のページ") {

                val pageitem = e.clickedInventory.getItem(49)
                val page = pageitem.itemMeta.displayName.toInt() - 1

                val gp = GuiProcess(plugin)
                p.openInventory(gp.allBookGuiCreate(p, page, inv.name))

            }

            if (item.type == Material.BLACK_SHULKER_BOX){
                val im = item.itemMeta as BlockStateMeta
                val sh = im.blockState as ShulkerBox

                val shinv = sh.inventory
                p.openInventory(shinv)
            }

        }

    }

}