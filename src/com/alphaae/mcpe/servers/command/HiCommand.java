package com.alphaae.mcpe.servers.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Position;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.MainPlugin;

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

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            UUID uuid = player.getUniqueId();
            //测试指令
//            RePlayer rePlayer = StaticData.rePlayerMap.get(uuid);
//            ChangePlayerCoinUtils.ReduceIcon(rePlayer, 100);
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putList(new ListTag<DoubleTag>("Pos").add(new DoubleTag("", 0)).add(new DoubleTag("", 0)).add(new DoubleTag("", 0)));
            compoundTag.putList(new ListTag<DoubleTag>("Motion").add(new DoubleTag("", 0)).add(new DoubleTag("", 0)).add(new DoubleTag("", 0)));
            compoundTag.putList(new ListTag<FloatTag>("Rotation").add(new FloatTag("", 0)).add(new FloatTag("", 0)));

            Position position = player.getPosition();

            player.sendMessage("" + player.getLevel().getSpawnLocation() + "  " + player.getLevel().getSafeSpawn());

//            MainPlugin.getPlugin().getServer().getScheduler().scheduleDelayedRepeatingTask(MainPlugin.getPlugin(), () -> {
//                try {
//                    EntityLightning sd = new EntityLightning(player.getChunk(), compoundTag);
//                    sd.setPosition(position);
//                    sd.spawnToAll();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }, 0, 10);

            return true;
        }
        return false;
    }
}
