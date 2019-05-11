package com.alphaae.mcpe.servers.model;

import java.io.Serializable;

public class UserLocation {
    private double x;
    private double y;
    private double z;
    private String levelName;

    public UserLocation(double x, double y, double z, String levelName) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.levelName = levelName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public String getLevelName() {
        return levelName;
    }
}
