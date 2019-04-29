package com.alphaae.mcpe.servers.model;

import cn.nukkit.Player;

public class RePlayer {

    private Player player;

    private String title;
    private int coin;

    public RePlayer(Player player, String title, int coin) {
        this.player = player;
        this.title = title;
        this.coin = coin;
    }

    public Player getPlayer() {
        return player;
    }


    public String getTitle() {
        return title;
    }

    public int getCoin() {
        return coin;
    }
}