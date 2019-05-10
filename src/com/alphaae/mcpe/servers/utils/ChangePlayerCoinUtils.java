package com.alphaae.mcpe.servers.utils;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.model.RePlayer;

public class ChangePlayerCoinUtils {

    public static boolean IncreaseIcon(RePlayer rePlayer, int count) {
        int userCoin = rePlayer.getCoin();
        int newCoin = userCoin + count;
        rePlayer.setCoin(newCoin);
        return PlayerDataUtils.ChangeData(rePlayer);
    }

    public static boolean ReduceIcon(RePlayer rePlayer, int count) {
        int userCoin = rePlayer.getCoin();
        int newCoin = userCoin - count;
        if (newCoin >= 0) {
            rePlayer.setCoin(newCoin);
            return PlayerDataUtils.ChangeData(rePlayer);
        }
        Player player = MainPlugin.getPlugin().getServer().getPlayer(rePlayer.getUuid()).get();
        player.sendMessage(TextFormat.colorize("&4硬币不足"));
        return false;
    }

    public static boolean GiveIcon(RePlayer rePlayer1, RePlayer rePlayer2, int count) {
        int userCoin1 = rePlayer1.getCoin();
        int userCoin2 = rePlayer2.getCoin();
        int newCoin1 = userCoin1 - count;
        int newCoin2 = userCoin2 + count;
        if (newCoin1 >= 0) {
            rePlayer1.setCoin(newCoin1);
            rePlayer2.setCoin(newCoin2);
            return PlayerDataUtils.ChangeData(rePlayer1) & PlayerDataUtils.ChangeData(rePlayer2);
        }
        Player player = MainPlugin.getPlugin().getServer().getPlayer(rePlayer1.getUuid()).get();
        player.sendMessage(TextFormat.colorize("&4硬币不足"));
        return false;
    }


}
