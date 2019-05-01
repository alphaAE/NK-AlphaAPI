package com.alphaae.mcpe.servers.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FormWindowOtherPlayer extends FormWindow {
    private final String type = "form";
    private String title;
    private String content;
    private List<ElementButton> buttons;
    private FormResponseSimple response;

    public FormWindowOtherPlayer(Player player) {
        this.title = "";
        this.content = "";
        this.response = null;

        String name = player.getName();
        String coin = "2000";

        String content = "" + name + "\n" +
                "---------------------------------\n---------------------------------\n" +
                "硬币：  " + coin + "\n" +
                "";

        this.title = name;
        this.content = content;
        this.buttons = new ArrayList();

        buttons.add(new ElementButton("", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "")));
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
