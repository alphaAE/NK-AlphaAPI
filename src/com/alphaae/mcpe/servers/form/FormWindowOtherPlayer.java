package com.alphaae.mcpe.servers.form;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FormWindowOtherPlayer extends FormWindowSimple {

    public FormWindowOtherPlayer(Player player, Player player2) {
        super("", "");
        createContents(player, player2);
        createButtons();
    }

    private void createContents(Player player, Player player2) {
        String name = player.getName();
        RePlayer rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        String name2 = player2.getName();
        RePlayer rePlayer2 = StaticData.rePlayerMap.get(player2.getUniqueId());
        String content = TextFormat.colorize("&b" + name2 + "&f\n" +
                "---------------------------------\n" +
                "称号：  " + rePlayer2.getTitle() + "\n" +
                "---------------------------------\n" +
                "");
        setTitle("玩家：" + name2);
        setContent(content);
    }

    private void createButtons() {
        addButton(new ElementButton("组队", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/iron_helmet.png")));
        addButton(new ElementButton("交易", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/emerald.png")));

    }

    public void RespondedEvent(PlayerFormRespondedEvent event) {
        String clickedText = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();
        Player player = event.getPlayer();

    }

}
