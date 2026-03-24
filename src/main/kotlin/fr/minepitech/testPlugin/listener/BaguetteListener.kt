package fr.minepitech.testPlugin.listener

import fr.minepitech.testPlugin.service.BaguetteItemService
import fr.minepitech.testPlugin.service.BaguetteService
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot


class BaguetteListener(
    private val baguetteItemService: BaguetteItemService,
    private val baguetteService: BaguetteService
) : Listener {
    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        if (event.hand != EquipmentSlot.HAND) return
        if (event.action != Action.RIGHT_CLICK_AIR && event.action != Action.RIGHT_CLICK_BLOCK) return

        val player = event.player
        if (!baguetteItemService.isBaguette(event.item)) return

        baguetteService.lancerBouleDefeu(player)
    }
}
