package fr.minepitech.testPlugin.manager

import org.bukkit.entity.Player
import java.util.UUID

class FlyProtectionManager {
    private val recentlyDisabled = mutableMapOf<UUID, Long>()

    fun markDisabled(player: Player) {
        recentlyDisabled[player.uniqueId] = System.currentTimeMillis()
    }

    fun consumeIfProtected(player: Player): Boolean {
        val timestamp = recentlyDisabled[player.uniqueId] ?: return false
        val isProtected = System.currentTimeMillis() - timestamp < PROTECTION_MS
        if (isProtected) {
            recentlyDisabled.remove(player.uniqueId)
            return true
        }

        // Nettoie les donnees expirees au premier acces.
        recentlyDisabled.remove(player.uniqueId)
        return false
    }

    companion object {
        private const val PROTECTION_MS = 5000L
    }
}

