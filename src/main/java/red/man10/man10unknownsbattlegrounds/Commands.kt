package red.man10.man10unknownsbattlegrounds

import org.bukkit.Material
import org.bukkit.block.ShulkerBox
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.BlockStateMeta

class Commands(val plugin: Man10UnknownsBattleGrounds): CommandExecutor {

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {

        if (args == null)return false
        if (sender == null)return false

        when(args.size){

            0->{

            }

            1->{

            }

            2->{

                if (args[0].equals("create", ignoreCase = true)){
                    if (plugin.arenamap.containsKey(args[1])){
                        sender.sendMessage("${plugin.prefix}§c既に登録されています")
                        return true
                    }
                    plugin.arenamap[args[1]] = Man10UnknownsBattleGrounds.ArenaInfo()
                    sender.sendMessage("${plugin.prefix}§c作成しました")
                    return true
                }

                if (args[0].equals("setspawn", ignoreCase = true)){

                    if (sender !is Player)return true

                    val p = sender as Player

                    if (!plugin.arenamap.containsKey(args[1])){
                        sender.sendMessage("${plugin.prefix}§c登録されていません")
                        return true
                    }

                    plugin.arenamap[args[1]]!!.spawnloc.add(p.location)
                    sender.sendMessage(plugin.prefix + "§a追加しました§f(${plugin.arenamap[args[1]]!!.spawnloc.size}箇所")
                    return true

                }

                if (args[0].equals("spawnlist", ignoreCase = true)){

                    if (!plugin.arenamap.containsKey(args[1])){
                        sender.sendMessage("${plugin.prefix}§c登録されていません")
                        return true
                    }

                    sender.sendMessage("§e§l<§b§lSpawn Location List§e§l>")

                    var count = 1

                    for (j in plugin.arenamap[args[1]]!!.spawnloc) {
                        sender.sendMessage("§e" + count + "§f§r: §aWorld§f:" + j.world.name.toString() + " / §aX§f:" + Math.round(j.x).toString() + " / §aY§f:" + Math.round(j.y).toString() + " / §aZ§f:" + Math.round(j.z).toString())
                        count++
                    }
                    return true
                }

                if (args[0].equals("itemloclist", ignoreCase = true)){

                    if (!plugin.arenamap.containsKey(args[1])){
                        sender.sendMessage("${plugin.prefix}§c登録されていません")
                        return true
                    }

                    sender.sendMessage("§e§l<§b§lItemSpawn Location List§e§l>")

                    var count = 1

                    for (j in plugin.arenamap[args[1]]!!.itemloc) {
                        sender.sendMessage("§e" + count + "§f§r: §aWorld§f:" + j.world.name.toString() + " / §aX§f:" + Math.round(j.x).toString() + " / §aY§f:" + Math.round(j.y).toString() + " / §aZ§f:" + Math.round(j.z).toString())
                        count++
                    }
                    return true
                }

                if (args[0].equals("itemlist", ignoreCase = true)){

                    if (sender !is Player)return true

                    val p = sender as Player

                    if (!plugin.arenamap.containsKey(args[1])){
                        sender.sendMessage("${plugin.prefix}§c登録されていません")
                        return true
                    }

                    val gui = GuiProcess(plugin)
                    gui.allBookGuiCreate(p, 1, args[1])

                }

            }

            3->{

                if (args[0].equals("additem", ignoreCase = true)){

                    if (sender !is Player)return true

                    val p = sender as Player

                    if (!plugin.arenamap.containsKey(args[1])){
                        sender.sendMessage("${plugin.prefix}§c登録されていません")
                        return true
                    }

                    val item = p.inventory.itemInMainHand

                    if (item.getItemMeta() !is BlockStateMeta) {
                        p.sendMessage(plugin.prefix + "§cシュルカーボックスを持ってください")
                        return true
                    }

                    var chance = 0.0

                    try {
                        chance = args[2].toDouble()
                    }catch (e: NumberFormatException){
                        p.sendMessage(plugin.prefix + "§c個数を入力してください")
                        return true
                    }

                    val im = item.itemMeta as BlockStateMeta
                    if (im.blockState !is ShulkerBox) {
                        p.sendMessage(plugin.prefix + "§cシュルカーボックスを持ってください")
                        return true
                    }

                    item.type = Material.BLACK_SHULKER_BOX

                    plugin.arenamap[args[1]]!!.itemmap[chance] = item

                    p.sendMessage(plugin.prefix + "§a追加しました§f(${plugin.arenamap[args[1]]!!.itemmap.size}個")

                }

                if (args[0].equals("setitemloc", ignoreCase = true)){

                    if (sender !is Player)return true

                    val p = sender as Player

                    if (!plugin.arenamap.containsKey(args[1])){
                        sender.sendMessage("${plugin.prefix}§c登録されていません")
                        return true
                    }

                    plugin.arenamap[args[1]]!!.itemloc.add(p.location)
                    sender.sendMessage(plugin.prefix + "§a追加しました§f(${plugin.arenamap[args[1]]!!.itemloc.size}箇所")
                    return true
                }

            }

        }

        return false
    }
    
}