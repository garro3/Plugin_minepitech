package fr.minepitech.testPlugin.manager

import org.bukkit.entity.Player
import java.util.UUID

/**
 * Gestionnaire des cooldowns pour éviter le spam
 *
 */
class CooldownManager {
    
    // Map qui stocke l'UUID du joueur et l'heure (en millisecondes) de sa dernière utilisation
    private val cooldowns = mutableMapOf<UUID, Long>()
    
    /**
     * Vérifie si le joueur est en cooldown
     */
    fun hasCooldown(player: Player): Boolean {
        val now = System.currentTimeMillis()  // Heure actuelle
        val lastUsed = cooldowns[player.uniqueId] ?: return false  // Récupère la dernière utilisation
        return now - lastUsed < COOLDOWN_MS  // Retourne true si le cooldown n'est pas fini
    }
    
    /**
     * Récupère le temps restant du cooldown en millisecondes
     */
    fun getRemainingCooldown(player: Player): Long {
        val now = System.currentTimeMillis()  // Heure actuelle
        val lastUsed = cooldowns[player.uniqueId] ?: return 0  // Récupère la dernière utilisation
        val remaining = COOLDOWN_MS - (now - lastUsed)  // Calcule le temps restant
        return if (remaining > 0) remaining else 0  // Retourne 0 si le cooldown est fini
    }
    
    /**
     * Applique un cooldown au joueur (tu dois l'appeler APRÈS avoir fait l'action)
     */
    fun setCooldown(player: Player) {
        cooldowns[player.uniqueId] = System.currentTimeMillis()  // Enregistre l'heure actuelle
    }
    
    companion object {
        private const val COOLDOWN_MS = 2000L  // 2000 millisecondes = 2 secondes
        // Change ce nombre pour modifier le temps de cooldown
        // 1000L = 1 seconde
        // 5000L = 5 secondes
        // 10000L = 10 secondes
    }
}

