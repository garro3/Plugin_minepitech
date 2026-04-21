package fr.minepitech.testPlugin.command

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.plugin.Plugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TimerCommand(private val plugin: Plugin) : CommandExecutor {

    private val mm = MiniMessage.miniMessage()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        val player = sender as? Player ?: return true

        if (args.isEmpty()) {
            player.sendMessage(mm.deserialize("<red>Usage: /timer <secondes>"))
            return true
        }
        val seconds = args[0].toIntOrNull()
        if (seconds == null || seconds <= 0) {
            player.sendMessage(mm.deserialize("<red>Veuillez fournir un nombre de secondes valide."))
            return true
        }

        var remaining = seconds

        object : BukkitRunnable() {
            override fun run() {
                if (!player.isOnline) {
                    cancel(); return
                }
                if (remaining > 0) {
                    player.sendActionBar(mm.deserialize("<yellow>Vous avez ${remaining} secondes restantes."))
                    remaining--
                } else {
                    player.sendActionBar(mm.deserialize("<green><bold> Timer terminé !"))
                    player.playSound(player.location, Sound.ENTITY_GENERIC_EXPLODE, 1f, 10f)
                    player.world.spawnParticle(Particle.EXPLOSION, player.location, 100)
                    cancel()
                }
            }
        }.runTaskTimer(plugin, 0L, 20L)

        player.sendMessage(mm.deserialize("<green> Timer de <white> $seconds <green>secondes lancé !"))
        return true
    }
}
