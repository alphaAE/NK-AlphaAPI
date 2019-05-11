package com.alphaae.mcpe.servers.utils;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.model.UserLocation;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.UUID;

public class PlayerDataUtils {

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
//            long begin = System.currentTimeMillis();
            File playerDataFile = new File(PLAYER_DATA_FOLDER, uuid.toString() + FILE_TYPE);
            if (!playerDataFile.exists()) {
                if (!CreateNewPlayerData(player))
                    return null;
            }
            //NIOs
            StringBuilder json = new StringBuilder();
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            FileInputStream inputStream = new FileInputStream(playerDataFile);
            FileChannel channel = inputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);          //初始化缓冲区大小
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            channel.read(byteBuffer);                                   //将文件通道里面的字节读到缓冲区中
            byteBuffer.flip();                                          //将position置为0，limit放到position位置
            decoder.decode(byteBuffer, charBuffer, false);  //解码
            charBuffer.flip();
            while (charBuffer.hasRemaining()) {
                json.append(charBuffer.get());
            }
            channel.close();
            inputStream.close();

            RePlayer rePlayer = RePlayer.decodeObject(json.toString()); //解码对象
            MainPlugin.getPlugin().getServer().getLogger().info("" + rePlayer.getJSONData());
//            long time = System.currentTimeMillis() - begin;
//            MainPlugin.getPlugin().getServer().getLogger().info("角色数据载入耗时：" + time + " ms");
            return rePlayer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean SaveData(RePlayer rePlayer) {

        File playerDataFile = new File(PLAYER_DATA_FOLDER, rePlayer.getUuid().toString() + FILE_TYPE);
        try {
            //NIO
            FileOutputStream outputStream = new FileOutputStream(playerDataFile);
            FileChannel channel = outputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.wrap(rePlayer.getJSONData().getBytes("UTF-8"));
            channel.write(buffer);
            channel.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean CreateNewPlayerData(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            //给予初始玩家的操作
            player.getInventory().setHeldItemSlot(1);
            player.getInventory().addItem(new Item(347));

            RePlayer rePlayer = new RePlayer(uuid, "新火", 2000);

            File playerDataFile = new File(PLAYER_DATA_FOLDER, uuid.toString() + FILE_TYPE);
            playerDataFile.createNewFile();
            return SaveData(rePlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
