package com.alphaae.mcpe.servers.form;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.level.Position;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.utils.ToastUtils;
import com.sun.istack.internal.NotNull;

public class FormWindowTeleportAllow extends FormWindowModal implements FormEvent {

    private transient Player player;
    private transient RePlayer rePlayer;

    public FormWindowTeleportAllow(@NotNull Player player) {
        super("玩家传送", "", "接受", "拒绝");
        this.player = player;
//        this.rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        String name = player.getDisplayName();
        setContent("玩家： " + name + " 请求传送到你身边");
    }

    @Override
    public void RespondedEvent(PlayerFormRespondedEvent event) {
        FormResponseModal formResponseModal = (FormResponseModal) event.getResponse();
        int clickedButtonId = formResponseModal.getClickedButtonId();
        Player player2 = event.getPlayer();
        if (clickedButtonId == 0) {
            Position position = player2.getPosition();
            player.teleport(position);
        } else {
            ToastUtils.Show(player, ToastUtils.INFO_TYPE_WARNING, "对方拒绝了传送");
        }
    }
}
