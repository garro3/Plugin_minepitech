package fr.minepitech.testPlugin.command

import fr.minepitech.testPlugin.service.BaguetteItemService
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.entity.Player


class Baguette(private val baguetteItemService: BaguetteItemService) : CommandExecutor {

    private val mm = MiniMessage.miniMessage()

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val player = sender as? Player ?: return true

        val item = ItemStack(Material.STICK)
        val meta = item.itemMeta
        meta.displayName(mm.deserialize("<gradient:red:yellow><b>Bite de feu</b></gradient>"))
        meta.lore(
            listOf(
                mm.deserialize("<red>Lance des boules de sperm.</red>"),
                mm.deserialize("<yellow>Clic droit pour tirer.</yellow>")
            )
        )
        meta.addEnchant(Enchantment.UNBREAKING, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        item.itemMeta = meta
        baguetteItemService.markAsBaguette(item)

        player.inventory.addItem(item)
        player.sendMessage(mm.deserialize("<green>Tu as recu la baguette de feu.</green>"))

        return true
    }
}