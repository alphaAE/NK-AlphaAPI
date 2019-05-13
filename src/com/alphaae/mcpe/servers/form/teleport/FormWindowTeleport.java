package com.alphaae.mcpe.servers.form.teleport;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;
import com.alphaae.mcpe.servers.form.FormWindowMeun;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.model.UserLocation;
import com.alphaae.mcpe.servers.utils.ToastUtils;
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
        initButtons();
    }

    private void initButtons() {
        addButton(new ElementButton("返回"));
        addButton(new ElementButton("上次死亡地点"));
        addButton(new ElementButton("玩家"));
        addButton(new ElementButton("主城"));
        Map<String, UserLocation> userLocationMap = rePlayer.getUserLocationMap();
        Iterator iterator = userLocationMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            if (key.equals("__deathPosition__")) continue;
            UserLocation value = (UserLocation) entry.getValue();
            addButton(new ElementButton(key + " ( " + value.getLevelName() + " )"));
        }
        addButton(new ElementButton("编辑储存的坐标"));
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
                    Map<String, UserLocation> userLocationMap = rePlayer.getUserLocationMap();
                    UserLocation deathUserLocation = userLocationMap.get("__deathPosition__");
                    if (deathUserLocation != null) {
                        Level level = MainPlugin.getPlugin().getServer().getLevelByName(deathUserLocation.getLevelName());
                        Position deathPosition = new Position(deathUserLocation.getX(), deathUserLocation.getY(), deathUserLocation.getZ(), level);
                        player.teleport(deathPosition);
                    } else {
                        ToastUtils.Show(player, ToastUtils.INFO_TYPE_ERROR, "不存在死亡记录点");
                    }
                    return;
                case "玩家":
                    FormWindowTeleportPlayer formWindowTeleportPlayer = new FormWindowTeleportPlayer(player);
                    player.showFormWindow(formWindowTeleportPlayer);
                    return;
                case "主城":
                    Position spawnLocation = player.getLevel().getSpawnLocation();
                    player.teleport(spawnLocation);
                    return;
                case "编辑储存的坐标":
                    FormWindowTeleportEdit formWindowTeleportEdit = new FormWindowTeleportEdit(player);
                    player.showFormWindow(formWindowTeleportEdit);
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
