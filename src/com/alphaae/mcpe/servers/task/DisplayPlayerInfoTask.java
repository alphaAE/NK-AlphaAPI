package com.alphaae.mcpe.servers.task;

import cn.nukkit.Player;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.Config;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.model.RePlayer;

import java.util.Map;
import java.util.UUID;

public class DisplayPlayerInfoTask {

    private Plugin plugin;

    private TaskHandler infoHandler;

    public DisplayPlayerInfoTask(Plugin plugin) {
        this.plugin = plugin;

        infoHandler = MainPlugin.getPlugin().getServer().getScheduler().scheduleDelayedRepeatingTask(MainPlugin.getPlugin(), () -> {
            try {
                float tps = MainPlugin.getPlugin().getServer().getTicksPerSecond();

                Map<UUID, Player> playerMap = plugin.getServer().getOnlinePlayers();
                for (Player tPlayer : playerMap.values()) {
                    RePlayer tRePlayer = StaticData.rePlayerMap.get(tPlayer.getUniqueId());
                    if (tRePlayer == null) continue;
                    String name = tPlayer.getDisplayName();
                    int ping = tPlayer.getPing();
                    int coin = tRePlayer.getCoin();
                    StringBuilder showText = new StringBuilder()
                            .append(name)
                            .append(" &f硬币: ").append(coin)
                            .append(" 延迟: ").append(ping).append("ms")
                            .append(" TPS: ").append(tps);
                    tPlayer.sendActionBar(TextFormat.colorize(showText.toString()));
                }
            } catch (Exception e) {
                if (infoHandler != null)
                    infoHandler.cancel();
                e.printStackTrace();
            }
        }, Config.JOIN_WAITING_TIME, 36);

    }

}
