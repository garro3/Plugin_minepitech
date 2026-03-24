package fr.minepitech.testPlugin.listener

import fr.minepitech.testPlugin.manager.FlyProtectionManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class FlyListener(private val flyProtectionManager: FlyProtectionManager) : Listener {
    @EventHandler

    fun onFall(event: EntityDamageEvent) {
        if (event.cause != EntityDamageEvent.DamageCause.FALL) return
        val player = event.entity as? Player ?: return
        if (flyProtectionManager.consumeIfProtected(player)) {
            event.isCancelled = true
        }
    }
}