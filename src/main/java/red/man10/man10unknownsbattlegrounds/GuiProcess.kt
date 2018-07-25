package red.man10.man10unknownsbattlegrounds

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class GuiProcess(val plugin: Man10UnknownsBattleGrounds) {

    fun allBookGuiCreate(p: Player, pagenumber: Int, key: String): Inventory {
        val itemmap = plugin.arenamap[key]!!.itemmap

        val itemlist = mutableListOf<ItemStack>()

        for (i in itemmap){
            itemlist.add(i.value)
        }

        val blank = ItemStack(Material.STAINED_GLASS_PANE, 1, 15)
        val blankm = blank.itemMeta
        blankm.displayName = ""
        blank.itemMeta = blankm

        val next = ItemStack(Material.PAPER)
        val nextm = next.itemMeta
        nextm.displayName = "§l次のページ"
        next.itemMeta = nextm

        val prev = ItemStack(Material.PAPER)
        val prevm = prev.itemMeta
        prevm.displayName = "§l前のページ"
        prev.itemMeta = prevm

        val page = ItemStack(Material.COMPASS)
        val pagem = page.itemMeta
        pagem.displayName = pagenumber.toString()
        page.itemMeta = pagem

        val inv: Inventory = Bukkit.getServer().createInventory(null, 54, key)

        if (pagenumber == 1) {
            inv.setItem(45, blank)
        }else{
            inv.setItem(45, prev)
        }

        inv.setItem(53, next)
        inv.setItem(46, blank)
        inv.setItem(47, blank)
        inv.setItem(48, blank)
        inv.setItem(49, page)
        inv.setItem(50, blank)
        inv.setItem(51, blank)
        inv.setItem(52, blank)

        var index = pagenumber * 45 - 45
        var endIndex= 0
        if (index + 45 >= itemlist.size){
            endIndex = itemlist.size
            inv.setItem(53, blank)
        }else {
            endIndex = index + 45
        }

        while (index < endIndex) {
            inv.setItem(index%45, itemlist[index])
            index++
        }

        return inv
    }

}