package com.alphaae.mcpe.servers.form.teleport;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.model.UserLocation;
import com.alphaae.mcpe.servers.utils.ToastUtils;
import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.Map;

public class FormWindowTeleportEdit extends FormWindowSimple implements FormEvent {

    private transient Player player;
    private transient RePlayer rePlayer;

    public FormWindowTeleportEdit(@NotNull Player player) {
        super("编辑记录的坐标", "");
        this.player = player;
        this.rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        initButtons();
    }

    private void initButtons() {
        addButton(new ElementButton("返回"));
        addButton(new ElementButton("添加当前坐标"));
        Map<String, UserLocation> userLocationMap = rePlayer.getUserLocationMap();
        Iterator iterator = userLocationMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            if (key.equals("__deathPosition__")) continue;
            UserLocation value = (UserLocation) entry.getValue();
            addButton(new ElementButton("删除 " + key + " ( " + value.getLevelName() + " )"));
        }
    }

    @Override
    public void RespondedEvent(PlayerFormRespondedEvent event) {
        String clickedText = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();
        Player player = event.getPlayer();
        Map<String, UserLocation> userLocationMap = rePlayer.getUserLocationMap();
        if (!event.wasClosed()) {
            switch (clickedText) {
                case "返回":
                    FormWindowTeleport formWindowTeleport = new FormWindowTeleport(player);
                    player.showFormWindow(formWindowTeleport);
                    return;
                case "添加当前坐标":
                    if (userLocationMap.size() < 6) {
                        FormWindowTeleportPut formWindowTeleportPut = new FormWindowTeleportPut(player);
                        player.showFormWindow(formWindowTeleportPut);
                    } else {
                        ToastUtils.Show(player, ToastUtils.INFO_TYPE_ERROR, "记录的坐标点超过上限");
                    }
                    return;
            }
            Iterator iterator = userLocationMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                UserLocation value = (UserLocation) entry.getValue();
                if (clickedText.equals("删除 " + key + " ( " + value.getLevelName() + " )")) {
                    userLocationMap.remove(key);
                    FormWindowTeleportEdit formWindowTeleportEdit = new FormWindowTeleportEdit(player);
                    player.showFormWindow(formWindowTeleportEdit);
                    return;
                }
            }
        }
    }
}
