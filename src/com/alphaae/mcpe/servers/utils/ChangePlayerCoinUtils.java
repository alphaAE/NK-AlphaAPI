package com.alphaae.mcpe.servers.utils;

import cn.nukkit.Player;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.model.RePlayer;

public class ChangePlayerCoinUtils {

    public static boolean IncreaseIcon(RePlayer rePlayer, int count) {
        int userCoin = rePlayer.getCoin();
        int newCoin = userCoin + count;
        rePlayer.setCoin(newCoin);
        return PlayerDataLoadUtils.ChangeData(rePlayer);
    }


    public static boolean ReduceIcon(RePlayer rePlayer, int count) {
        int userCoin = rePlayer.getCoin();
        int newCoin = userCoin - count;
        if (newCoin >= 0) {
            rePlayer.setCoin(newCoin);
            return PlayerDataLoadUtils.ChangeData(rePlayer);
        }
        Player player = MainPlugin.getPlugin().getServer().getPlayer(rePlayer.getUuid()).get();
        player.sendMessage("");
        return false;
    }


}
