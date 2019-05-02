package com.alphaae.mcpe.servers.event;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEntityEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerItemHeldEvent;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.form.FormWindowMeun;
import com.alphaae.mcpe.servers.form.FormWindowOtherPlayer;

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

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getEntity();
        Item item = event.getItem();
        if (player != null && entity != null && item != null) {
            try {
                Player player2 = (Player) entity;
//                player.sendMessage("你触碰了玩家" + player.getName());
//                player2.sendMessage("你被" + player.getName() + "触碰了");
                FormWindowOtherPlayer formWindowOtherPlayer = new FormWindowOtherPlayer(player, player2);
                player.showFormWindow(formWindowOtherPlayer);
            } catch (Exception e) {
                player.sendMessage(TextFormat.colorize("&4非玩家单位"));
            }
        }
    }


}
