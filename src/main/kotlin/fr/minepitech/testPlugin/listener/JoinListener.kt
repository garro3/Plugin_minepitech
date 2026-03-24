package fr.minepitech.testPlugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import fr.minepitech.testPlugin.service.WelcomeService

class JoinListener(private val welcomeService: WelcomeService) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        welcomeService.welcomePlayer(event.player)
    }

}