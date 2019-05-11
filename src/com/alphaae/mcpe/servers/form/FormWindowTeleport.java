package com.alphaae.mcpe.servers.form;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.model.UserLocation;
import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.Map;

public class FormWindowTeleport extends FormWindowSimple implements FormEvent {

    private transient Player player;
    private transient RePlayer rePlayer;

    public FormWindowTeleport(@NotNull Player player) {
        super("传送地点", "");
        this.player = player;
        this.rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        createContents();
        initButtons();
    }

    private void createContents() {
    }

    private void initButtons() {
        addButton(new ElementButton("返回"));
        addButton(new ElementButton("上次死亡地点"));
        addButton(new ElementButton("主城"));
        Map<String, UserLocation> userLocationMap = rePlayer.getUserLocationMap();
        Iterator iterator = userLocationMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            UserLocation value = (UserLocation) entry.getValue();
            addButton(new ElementButton(key + " ( " + value.getLevelName() + " )"));
        }
    }

    public void RespondedEvent(PlayerFormRespondedEvent event) {
//        long begin = System.currentTimeMillis();
        String clickedText = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();
        Player player = event.getPlayer();
        if (!event.wasClosed()) {
            switch (clickedText) {
                case "返回":
                    FormWindowMeun formWindowMeun = new FormWindowMeun(player);
                    player.showFormWindow(formWindowMeun);
                    return;
                case "上次死亡地点":
                    player.sendMessage("" + player.getLevel().getName());
                    return;
                case "主城":
                    Position spawnLocation = player.getLevel().getSpawnLocation();
                    player.teleport(spawnLocation);
                    return;
            }
            Map<String, UserLocation> userLocationMap = rePlayer.getUserLocationMap();
            Iterator iterator = userLocationMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                UserLocation value = (UserLocation) entry.getValue();
                if (clickedText.equals(key + " ( " + value.getLevelName() + " )")) {
                    Level level = MainPlugin.getPlugin().getServer().getLevelByName(value.getLevelName());
                    Position position = new Position(value.getX(), value.getY(), value.getZ(), level);
                    player.teleport(position);
//                    long time = System.currentTimeMillis() - begin;
//                    MainPlugin.getPlugin().getServer().getLogger().info("耗时：" + time + " ms");
                    return;
                }
            }


        }
    }

}
