package com.alphaae.mcpe.servers.event.block.joinquit;

import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;

public interface JoinQuitEventBlock {
    void onPlayerJoin(PlayerJoinEvent event);

    void onPlayerQuit(PlayerQuitEvent event);
}
