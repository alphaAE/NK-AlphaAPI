package com.alphaae.mcpe.servers.form;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.sun.istack.internal.NotNull;

public class FormWindowTeleport extends FormWindowSimple implements FormEvent {

    public FormWindowTeleport(@NotNull Player player) {
        super("传送地点", "");
        RePlayer rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        createContents(player, rePlayer);
        createButtons(player, rePlayer);
    }

    private void createContents(Player player, RePlayer rePlayer) {
    }

    private void createButtons(Player player, RePlayer rePlayer) {
        addButton(new ElementButton("返回"));
        addButton(new ElementButton("上次死亡地点"));
        addButton(new ElementButton("主城"));
        addButton(new ElementButton(""));
    }

    public void RespondedEvent(PlayerFormRespondedEvent event) {
        String clickedText = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();
        Player player = event.getPlayer();
        if (!event.wasClosed()) {
            switch (clickedText) {
                case "返回":
                    FormWindowMeun formWindowMeun = new FormWindowMeun(player);
                    player.showFormWindow(formWindowMeun);
                    break;
                case "上次死亡地点":
                    player.sendMessage("" + player.getLevel().getName());
                    break;
                case "主城":
                    Position spawnLocation = player.getLevel().getSpawnLocation();
                    player.teleport(spawnLocation);
                    break;

            }
        }
    }

}
