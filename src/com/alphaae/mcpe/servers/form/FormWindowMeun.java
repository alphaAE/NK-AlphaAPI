package com.alphaae.mcpe.servers.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FormWindowMeun extends FormWindow {
    private final String type = "form";
    private String title;
    private String content;
    private List<ElementButton> buttons;
    private FormResponseSimple response;

    public FormWindowMeun(Player player) {
        this.title = "我";
        this.buttons = new ArrayList();

        String name = player.getName();
        RePlayer rePlayer = StaticData.rePlayerMap.get(player.getUniqueId());

        this.content = TextFormat.colorize("&b" + name + "&f\n" +
                "---------------------------------\n" +
                "称号：  " + rePlayer.getTitle() + "\n" +
                "硬币：  " + rePlayer.getCoin() + "\n" +
                "---------------------------------\n" +
                "");

        buttons.add(new ElementButton("传送", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/ender_pearl.png")));
        buttons.add(new ElementButton("任务", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/book_writable.png")));
        buttons.add(new ElementButton("升级", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/iron_pickaxe.png")));
        
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ElementButton> getButtons() {
        return this.buttons;
    }

    public void addButton(ElementButton button) {
        this.buttons.add(button);
    }

    public String getJSONData() {
        return (new Gson()).toJson(this);
    }

    public FormResponseSimple getResponse() {
        return this.response;
    }

    public void setResponse(String data) {
        if (data.equals("null")) {
            this.closed = true;
        } else {
            int buttonID;
            try {
                buttonID = Integer.parseInt(data);
            } catch (Exception var4) {
                return;
            }

            if (buttonID >= this.buttons.size()) {
                this.response = new FormResponseSimple(buttonID, (ElementButton) null);
            } else {
                this.response = new FormResponseSimple(buttonID, (ElementButton) this.buttons.get(buttonID));
            }
        }
    }
}
