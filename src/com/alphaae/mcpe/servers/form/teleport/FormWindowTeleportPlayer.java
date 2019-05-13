package com.alphaae.mcpe.servers.form.teleport;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.utils.ToastUtils;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FormWindowTeleportPlayer extends FormWindowCustom implements FormEvent {

    private transient Player player;
    private transient RePlayer rePlayer;

    public FormWindowTeleportPlayer(@NotNull Player player) {
        super("传送到某玩家");
        this.player = player;
//        this.rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        initElements();
    }

    private void initElements() {
        Map<UUID, Player> playerMap = MainPlugin.getPlugin().getServer().getOnlinePlayers();
        List<String> playerList = new ArrayList<>();
        for (Player tPlayer : playerMap.values()) {
            String name = tPlayer.getName();
            if (name.equals(player.getName())) continue;
            playerList.add(tPlayer.getName());
        }
        addElement(new ElementDropdown("选择玩家：", playerList));
        addElement(new ElementLabel("\n\n\n\n\n\n\n\n"));
    }

    @Override
    public void RespondedEvent(PlayerFormRespondedEvent event) {
        FormResponseCustom formResponseCustom = (FormResponseCustom) event.getResponse();
        String posName = formResponseCustom.getDropdownResponse(0).getElementContent();
        Player player = event.getPlayer();
        if (!posName.equals("")) {
            Player player2 = MainPlugin.getPlugin().getServer().getPlayer(posName);
            if (player2 != null) {
                FormWindowTeleportAllow formWindowTeleportAllow = new FormWindowTeleportAllow(player);
                player2.showFormWindow(formWindowTeleportAllow);
                ToastUtils.Show(player, ToastUtils.INFO_TYPE_INFO, "传送请求已发送");
            } else {
                ToastUtils.Show(player, ToastUtils.INFO_TYPE_ERROR, "玩家不在游戏中");
            }
        }

    }

}
