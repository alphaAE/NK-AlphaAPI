package com.alphaae.mcpe.servers.model;

import java.io.Serializable;
import java.util.UUID;

public class RePlayer implements Serializable {
    private UUID uuid;
    //称号
    private String title;
    //硬币
    private int coin;

    public RePlayer(UUID uuid, String title, int coin) {
        this.uuid = uuid;
        this.title = title;
        this.coin = coin;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}