@file:Suppress("unused")

package fr.minepitech.testPlugin

import fr.minepitech.testPlugin.command.Baguette
import fr.minepitech.testPlugin.command.FlyCommand
import fr.minepitech.testPlugin.manager.FlyProtectionManager
import fr.minepitech.testPlugin.listener.BaguetteListener
import fr.minepitech.testPlugin.listener.FlyListener
import fr.minepitech.testPlugin.listener.JoinListener
import fr.minepitech.testPlugin.service.WelcomeService
import fr.minepitech.testPlugin.service.BaguetteService
import fr.minepitech.testPlugin.service.BaguetteItemService
import fr.minepitech.testPlugin.manager.CooldownManager
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        logger.info("Plugin de clément enabled")

        // Créer les services et managers
        val welcomeService = WelcomeService()
        val cooldownManager = CooldownManager()
        val flyProtectionManager = FlyProtectionManager()
        val baguetteItemService = BaguetteItemService(this)
        val baguetteService = BaguetteService(cooldownManager)

        // Enregistrer les commandes
        getCommand("clemFly")?.setExecutor(FlyCommand(flyProtectionManager))
        getCommand("clemBaguette")?.setExecutor(Baguette(baguetteItemService))

        // Enregistrer les listeners
        server.pluginManager.registerEvents(FlyListener(flyProtectionManager), this)
        server.pluginManager.registerEvents(BaguetteListener(baguetteItemService, baguetteService), this)
        server.pluginManager.registerEvents(JoinListener(welcomeService), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}

