package com.alphaae.mcpe.servers.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryEvent implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = event.getPlayer();
        int slot = event.getSlot();
        player.sendMessage("Solt: " + slot);
    }
}
