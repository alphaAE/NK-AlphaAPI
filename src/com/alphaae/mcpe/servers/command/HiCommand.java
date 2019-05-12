package com.alphaae.mcpe.servers.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.model.RePlayer;
import com.alphaae.mcpe.servers.model.UserLocation;
import com.alphaae.mcpe.servers.utils.ChangePlayerCoinUtils;

import java.util.UUID;

public class HiCommand extends Command {
    private MainPlugin plugin;

    public HiCommand(MainPlugin plugin) {
        super("hi", "Hi测试指令", "/hi");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!this.plugin.isEnabled()) return false;
        if (!commandSender.hasPermission("alphaapi.command.everybody")) {
            commandSender.sendMessage(TextFormat.RED + "你没有权限使用该指令");
            return false;
        }

        if (commandSender.isPlayer()) {
            Player player = commandSender.getServer().getPlayer(commandSender.getName());
            UUID uuid = player.getUniqueId();
            //测试指令
            RePlayer rePlayer = StaticData.rePlayerMap.get(uuid);
            ChangePlayerCoinUtils.ReduceIcon(rePlayer, 100);
            return true;
        }
        return false;
    }
}
