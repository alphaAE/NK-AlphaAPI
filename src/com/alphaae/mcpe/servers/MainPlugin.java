package com.alphaae.mcpe.servers;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import com.alphaae.mcpe.servers.command.HiCommand;
import com.alphaae.mcpe.servers.command.UpdataUserDataCommand;
import com.alphaae.mcpe.servers.event.*;

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
        commandMap.register("AlphaAPI", new UpdataUserDataCommand(this));
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinQuitEvent(), this);
        pluginManager.registerEvents(new PlayerInteractSetEvent(), this);
        pluginManager.registerEvents(new FormRespondedEvent(), this);
        pluginManager.registerEvents(new PlayerDeathRespawnEvent(), this);
//        pluginManager.registerEvents(new PlayerInventoryEvent(), this);
    }
}
