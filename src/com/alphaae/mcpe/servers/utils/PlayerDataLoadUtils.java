package com.alphaae.mcpe.servers.utils;

import cn.nukkit.Player;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.model.RePlayer;

import java.io.*;
import java.util.UUID;

public class PlayerDataLoadUtils {

    private static final String FILE_TYPE = ".replayer";
    private static final File DATA_FOLDER = MainPlugin.getPlugin().getDataFolder();
    private static final File PLAYER_DATA_FOLDER = new File(DATA_FOLDER, "PlayerData");

    static {
        if (!PLAYER_DATA_FOLDER.exists()) {
            PLAYER_DATA_FOLDER.mkdirs();
        }
    }

    public static RePlayer LoadData(Player player) {
        UUID uuid = player.getUniqueId();
        try {
            File playerDataFile = new File(PLAYER_DATA_FOLDER, uuid.toString() + FILE_TYPE);
            if (!playerDataFile.exists()) {
                CreateNewPlayerData(playerDataFile, uuid);
            }
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(playerDataFile));
            RePlayer rePlayer = (RePlayer) inputStream.readObject();
            return rePlayer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean ChangeData(RePlayer rePlayer) {

        File playerDataFile = new File(PLAYER_DATA_FOLDER, rePlayer.getUuid().toString() + FILE_TYPE);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(playerDataFile, false));
            outputStream.writeObject(rePlayer);
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean CreateNewPlayerData(File playerDataFile, UUID uuid) {
        try {
            playerDataFile.createNewFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(playerDataFile));
            RePlayer rePlayer = new RePlayer(uuid, "新火", 2000);
            outputStream.writeObject(rePlayer);
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
