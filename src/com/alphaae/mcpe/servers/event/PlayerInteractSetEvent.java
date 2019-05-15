package com.alphaae.mcpe.servers.event;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerInteractEntityEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerItemHeldEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.form.FormWindowMeun;
import com.alphaae.mcpe.servers.form.FormWindowOtherPlayer;

public class PlayerInteractSetEvent implements Listener {

    //玩家长按交互事件
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
//        Player player = event.getPlayer();
//        Item item = event.getItem();
//        Block block = event.getBlock();
//        if (player != null && item != null) {
//            if (item.getId() == 0 && block.getId() == 0) {
//                FormWindowMeun form = new FormWindowMeun(player);
//                player.showFormWindow(form);
//            }
//        }
    }

    //玩家手持物品事件
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Plugin plugin = MainPlugin.getPlugin();
        Player player = event.getPlayer();
        Item item = event.getItem();
        final int slot = event.getSlot();
        if (player != null && item != null) {
            if (item.getId() == 347) {
                if (slot != 0) {
                    player.getInventory().setHeldItemSlot(0);
                } else {
                    player.getInventory().setHeldItemSlot(1);
                }
                FormWindowMeun formWindowMeun = new FormWindowMeun(player);
                player.showFormWindow(formWindowMeun);
            }
        }
    }

    //玩家长按触碰实体
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getEntity();
//        Item item = event.getItem();
        if (player != null && entity != null) {
            try {
                Player player2 = (Player) entity;
                FormWindowOtherPlayer formWindowOtherPlayer = new FormWindowOtherPlayer(player, player2);
                player.showFormWindow(formWindowOtherPlayer);
            } catch (Exception e) {
                player.sendMessage(TextFormat.colorize("&4非玩家单位"));
            }
        }
    }

    //测试事件
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerChat(PlayerChatEvent event) {
        final Player player = event.getPlayer();
        if (player instanceof Player) {
            Position position = player.getPosition();
            String msg = event.getMessage();
            if (msg.equals("发送坐标")) {
                StringBuilder showText = new StringBuilder()
                        .append("[坐标] : 我在 ").append(player.getLevel().getName()).append("世界的")
                        .append(" x: ").append(position.getX())
                        .append(" y: ").append(position.getY())
                        .append(" z: ").append(position.getZ())
                        .append(" 坐标");
                player.sendMessage(showText.toString());
            }
        }
    }


}
