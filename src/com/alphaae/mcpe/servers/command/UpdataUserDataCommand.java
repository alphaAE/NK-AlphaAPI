package com.alphaae.mcpe.servers.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.MainPlugin;

public class UpdataUserDataCommand extends Command {
    private MainPlugin plugin;

    public UpdataUserDataCommand(MainPlugin plugin) {
        super("updatauserdata", "更新用户数据指令", "/updatauserdata");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!this.plugin.isEnabled()) return false;
        if (!commandSender.hasPermission("alphaapi.command.console")) {
            commandSender.sendMessage(TextFormat.RED + "你没有权限使用该指令");
            return false;
        }

        if (commandSender.isPlayer()) {

            return true;
        }
        return false;
    }
}
