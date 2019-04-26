package com.alphaae.mcpe.servers;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;

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
                    player.sendTitle("Hi TiTle");
                    player.sendPopup("Hi Popup");
                    player.sendActionBar("Hi ActionBar");
                    player.sendTip("Hi Tip");
                }
                sender.sendMessage("Hi " + sender.getName());
                return true;
//
//            case "menu":
//                player.chat("/el open default.yml");
//                return true;
        }
        return false;
    }
}
