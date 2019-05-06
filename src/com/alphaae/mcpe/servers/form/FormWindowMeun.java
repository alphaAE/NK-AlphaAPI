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

public class FormWindowMeun extends FormWindowSimple {

    public FormWindowMeun(Player player) {
        super("我", "");
        createContents(player);
        createButtons();
    }

    private void createContents(Player player) {
        String name = player.getName();
        RePlayer rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        String content = TextFormat.colorize("&b" + name + "&f\n" +
                "---------------------------------\n" +
                "称号：  " + rePlayer.getTitle() + "\n" +
                "硬币：  " + rePlayer.getCoin() + "\n" +
                "---------------------------------\n" +
                "");
        setContent(content);
    }

    private void createButtons() {
        addButton(new ElementButton("传送", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/ender_pearl.png")));
        addButton(new ElementButton("任务", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/book_writable.png")));
        addButton(new ElementButton("升级", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/iron_pickaxe.png")));
    }

    public void RespondedEvent(PlayerFormRespondedEvent event) {
        String clickedText = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();
        Player player = event.getPlayer();
        if (!event.wasClosed()) {
            switch (clickedText){
                case "传送":
                    player.sendMessage("传送");
                    break;
                case "任务":
                    player.sendMessage("任务");
                    break;
                case "升级":
                    player.sendMessage("升级");
                    break;
            }
        }
    }

}
