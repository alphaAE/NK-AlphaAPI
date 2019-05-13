package com.alphaae.mcpe.servers.form;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;
import com.alphaae.mcpe.servers.form.teleport.FormWindowTeleport;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.sun.istack.internal.NotNull;

public class FormWindowMeun extends FormWindowSimple implements FormEvent {

    private transient Player player;
    private transient RePlayer rePlayer;

    public FormWindowMeun(@NotNull Player player) {
        super("我", "");
        this.player = player;
        this.rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());
        createContents();
        initButtons();
    }

    private void createContents() {
        String name = player.getName();
        String content = TextFormat.colorize("&b" + name + "&f\n" +
                "---------------------------------\n" +
                "称号：  " + rePlayer.getTitle() + "\n" +
                "硬币：  " + rePlayer.getCoin() + "\n" +
                "---------------------------------\n" +
                "");
        setContent(content);
    }

    private void initButtons() {
        addButton(new ElementButton("传送", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/ender_pearl.png")));
        addButton(new ElementButton("任务", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/book_writable.png")));
        addButton(new ElementButton("升级", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/iron_pickaxe.png")));
    }

    @Override
    public void RespondedEvent(PlayerFormRespondedEvent event) {
        String clickedText = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();
        Player player = event.getPlayer();
        if (!event.wasClosed()) {
            switch (clickedText) {
                case "传送":
                    FormWindowTeleport formWindowTeleport = new FormWindowTeleport(player);
                    player.showFormWindow(formWindowTeleport);
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
