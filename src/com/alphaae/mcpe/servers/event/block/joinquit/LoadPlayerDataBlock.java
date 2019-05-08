package com.alphaae.mcpe.servers.event.block.joinquit;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.utils.PlayerDataUtils;

public class LoadPlayerDataBlock implements JoinQuitEventBlock {

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //读取数据
        RePlayer rePlayer = PlayerDataUtils.LoadData(player);

        String title = rePlayer.getTitle();

        player.setNameTag(TextFormat.colorize("&e[" + title + "] &b" + player.getName() + "&f"));
        player.setDisplayName(TextFormat.colorize("&e[" + title + "] &b" + player.getName() + "&f"));
        StaticData.rePlayerMap.put(player.getUniqueId(), rePlayer);
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        StaticData.rePlayerMap.remove(player.getUniqueId());
    }


}
