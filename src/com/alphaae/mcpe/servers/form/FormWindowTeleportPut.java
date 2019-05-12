package com.alphaae.mcpe.servers.form;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.model.UserLocation;
import com.alphaae.mcpe.servers.utils.ToastUtils;
import com.sun.istack.internal.NotNull;

public class FormWindowTeleportPut extends FormWindowCustom implements FormEvent {

    private transient Player player;
    private transient RePlayer rePlayer;

    public FormWindowTeleportPut(@NotNull Player player) {
        super("添加记录坐标");
        this.player = player;
        this.rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        initElements();
    }

    private void initElements() {
        addElement(new ElementInput("坐标点名字："));
        addElement(new ElementLabel("\n\n\n\n\n\n\n\n"));
    }

    @Override
    public void RespondedEvent(PlayerFormRespondedEvent event) {
        FormResponseCustom formResponseCustom = (FormResponseCustom) event.getResponse();
        String posName = formResponseCustom.getInputResponse(0);
        Player player = event.getPlayer();
        if (!posName.equals("")) {
            rePlayer.putUserLocation(posName, new UserLocation(player.getX(), player.getY(), player.getZ(), player.getLevel().getName()));
            FormWindowTeleportEdit formWindowTeleportEdit = new FormWindowTeleportEdit(player);
            player.showFormWindow(formWindowTeleportEdit);
        } else {
            ToastUtils.Show(player, ToastUtils.INFO_TYPE_ERROR, "坐标点名不能为空");
        }

    }
}
