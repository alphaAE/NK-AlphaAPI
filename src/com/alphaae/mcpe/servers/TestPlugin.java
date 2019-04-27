package com.alphaae.mcpe.servers;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;

public class TestPlugin extends PluginBase {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        getLogger().info("onLoad! QwQ");
    }

    @Override
    public void onEnable() {
        plugin = this;
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new InfoDisplay(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = sender.getServer().getPlayer(sender.getName());
        switch (command.getName()) {
            case "hi":
                if (sender.isPlayer()) {
//                    List<Element> list = new ArrayList();
//                    list.add(new ElementDropdown("2333"));
//                    list.add(new ElementInput("输入"));
//                    FormWindowCustom form = new FormWindowCustom("title", list);
////                    form.addButton(ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/melon.png"));
//                    player.showFormWindow()
                }
                return true;

//            case "menu":
//                player.chat("/el open default.yml");
//                return true;
        }
        return false;
    }
}
