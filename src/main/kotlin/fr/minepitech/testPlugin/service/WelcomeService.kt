package fr.minepitech.testPlugin.service

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class WelcomeService {
    
    fun welcomePlayer(player: Player) {

        val mm = MiniMessage.miniMessage()

        // Message de bienvenue
        player.sendMessage("Bienvenue sur le serveur de clément le boss, le plus beau chauve de tout les temps !")
        
        // Ajouter les items
        val sword = ItemStack(Material.NETHERITE_SWORD, 1)
        val metaSword = sword.itemMeta
        metaSword.displayName(mm.deserialize("Le gros paf de clem"))
        metaSword.addEnchant(Enchantment.UNBREAKING, 1, true)
        metaSword.addEnchant(Enchantment.SHARPNESS, 3, true)
        sword.itemMeta = metaSword
        player.inventory.addItem(sword)
        player.updateInventory()
    }
}

