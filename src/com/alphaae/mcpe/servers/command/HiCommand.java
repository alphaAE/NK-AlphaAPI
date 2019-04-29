package com.alphaae.mcpe.servers.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.form.FormWindowMy;

public class HiCommand extends Command {
    private MainPlugin plugin;

    public HiCommand(MainPlugin plugin) {
        super("hi", "Hi测试指令", "/hi");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!this.plugin.isEnabled()) return false;
        if (!commandSender.hasPermission("testplugin.command.menu")) {
            commandSender.sendMessage(TextFormat.RED + "你没有权限使用该指令");
            return false;
        }

        Player player = commandSender.getServer().getPlayer(commandSender.getName());
        if (commandSender.isPlayer()) {
            FormWindowMy form = new FormWindowMy(player);
            player.showFormWindow(form);
            return true;
        }
        return false;
    }
}
