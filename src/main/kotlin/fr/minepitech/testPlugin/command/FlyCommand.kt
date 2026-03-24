package fr.minepitech.testPlugin.command

import fr.minepitech.testPlugin.manager.FlyProtectionManager
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class FlyCommand(private val flyProtectionManager: FlyProtectionManager) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {

        val player = sender as? Player ?: return true
        player.allowFlight = !player.allowFlight
        // Créer le message avec MiniMessage et couleurs
        val mm = MiniMessage.miniMessage()
        val msg = if (player.allowFlight)
            mm.deserialize("<green>Vous pouvez désormais voler grace à clément le boss !</green>")
        else
            mm.deserialize("<red>Vous ne pouvez plus voler !</red>")

        // Envoyer le message au joueur
        player.sendMessage(msg)

        if (!player.allowFlight) {
            flyProtectionManager.markDisabled(player)
        }


        return true
    }
}