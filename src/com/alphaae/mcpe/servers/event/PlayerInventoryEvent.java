package com.alphaae.mcpe.servers.event;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryEvent implements Listener {

    //点击物品栏事件
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onInventoryClick(InventoryClickEvent event) {
        //在背包打开状态下才有效
    }
}
