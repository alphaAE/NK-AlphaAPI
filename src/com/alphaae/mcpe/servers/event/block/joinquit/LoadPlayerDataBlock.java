package com.alphaae.mcpe.servers.event.block.joinquit;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.model.RePlayer;

public class LoadPlayerDataBlock implements JoinQuitEventBlock {

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //读取数据

        String title = "[称号]";

        player.setDisplayName(TextFormat.colorize("&e" + title + " &b" + player.getName() + "&f"));

        RePlayer rePlayer = new RePlayer(player, title, 4000);
        StaticData.rePlayerMap.put(player.getUniqueId(), rePlayer);
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        StaticData.rePlayerMap.remove(player.getUniqueId());
    }
}
