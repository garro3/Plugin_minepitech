package fr.minepitech.testPlugin.service

import fr.minepitech.testPlugin.manager.CooldownManager
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player

class BaguetteService(private val cooldownManager: CooldownManager) {
    
    fun lancerBouleDefeu(player: Player): Boolean {
        // Vérifier le cooldown
        if (cooldownManager.hasCooldown(player)) {
            val remaining = cooldownManager.getRemainingCooldown(player) / 1000
            player.sendMessage("§cCooldown ! Réessaye dans ${remaining}s")
            return false
        }
        
        // Lancer la boule de feu
        val direction = player.eyeLocation.direction.multiply(2.0)
        val spawnLocation = player.eyeLocation.clone().add(direction)
        player.world.spawn(spawnLocation, Fireball::class.java) { fireball ->
            fireball.direction = direction
            fireball.shooter = player
        }
        
        // Appliquer le cooldown
        cooldownManager.setCooldown(player)
        player.sendMessage("<rouge>DANS TA MERE !</rouge>")
        
        return true
    }
}

