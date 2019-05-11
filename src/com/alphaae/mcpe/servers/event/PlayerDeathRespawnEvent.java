package com.alphaae.mcpe.servers.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.model.UserLocation;

public class PlayerDeathRespawnEvent implements Listener {

    //玩家死亡事件
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        RePlayer rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());

        //记录死亡地点
        Position deathPosition = player.getPosition();
        rePlayer.putUserLocation("__deathPosition__", new UserLocation(deathPosition.getX(), deathPosition.getY(), deathPosition.getZ(), player.getLevel().getName()));
    }

    //玩家重生事件
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.getInventory().setHeldItemSlot(1);
        player.getInventory().addItem(new Item(347));
    }
}
