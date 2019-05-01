package com.alphaae.mcpe.servers.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerItemHeldEvent;
import cn.nukkit.item.Item;
import com.alphaae.mcpe.servers.form.FormWindowMeun;

public class PlayerInteractSetEvent implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Item item = event.getItem();
        if (player != null && item != null) {
            if (item.getId() == 347) {
                FormWindowMeun form = new FormWindowMeun(player);
                player.showFormWindow(form);
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Item item = event.getItem();
        if (player != null && item != null) {
            if (item.getId() == 347) {

            }
        }
    }

}
