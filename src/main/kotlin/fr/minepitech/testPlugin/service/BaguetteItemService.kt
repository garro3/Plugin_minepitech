package fr.minepitech.testPlugin.service

import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class BaguetteItemService(plugin: JavaPlugin) {
    private val baguetteKey = NamespacedKey(plugin, BAGUETTE_KEY)

    fun markAsBaguette(item: ItemStack) {
        val meta = item.itemMeta
        meta.persistentDataContainer.set(baguetteKey, PersistentDataType.BYTE, 1)
        item.itemMeta = meta
    }

    fun isBaguette(item: ItemStack?): Boolean {
        val meta = item?.itemMeta ?: return false
        return meta.persistentDataContainer.getOrDefault(baguetteKey, PersistentDataType.BYTE, 0) == 1.toByte()
    }

    companion object {
        private const val BAGUETTE_KEY = "bite_feu"
    }
}

