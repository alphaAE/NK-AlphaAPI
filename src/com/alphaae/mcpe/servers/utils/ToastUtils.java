package com.alphaae.mcpe.servers.utils;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;

public class ToastUtils {

    public static final int INFO_TYPE_ERROR = 0;


    private static String[] infoTypeArr = new String[]{"&4"};

    public static void Show(Player player, int infoType, String info) {
        player.sendPopup(TextFormat.colorize(infoTypeArr[infoType] + info));
    }

}
