package com.alphaae.mcpe.servers;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import com.alphaae.mcpe.servers.command.HiCommand;
import com.alphaae.mcpe.servers.event.PlayerInteractSetEvent;
import com.alphaae.mcpe.servers.event.PlayerJoinQuitEvent;

public class MainPlugin extends PluginBase {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        getLogger().info("AlphaAPI被加载！");
    }

    @Override
    public void onEnable() {
        plugin = this;
        initConfig();
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void initConfig() {
        getDataFolder().mkdirs();
        saveResource("config.yml");
        reloadConfig();
    }

    private void registerCommands() {
        SimpleCommandMap commandMap = getServer().getCommandMap();
        commandMap.register("AlphaAPI", new HiCommand(this));
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinQuitEvent(), this);
        pluginManager.registerEvents(new PlayerInteractSetEvent(), this);
    }
}
