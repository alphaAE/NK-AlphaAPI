package com.alphaae.mcpe.servers.event.block.joinquit;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.Config;
import com.alphaae.mcpe.servers.MainPlugin;
import com.alphaae.mcpe.servers.StaticData;
import com.alphaae.mcpe.servers.model.RePlayer;

import java.util.UUID;

public class DisplayInfoBlock implements JoinQuitEventBlock {

    private TaskHandler infoHandler;

    public DisplayInfoBlock() {
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        final String name = player.getDisplayName();

        try {
            infoHandler = MainPlugin.getPlugin().getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
                @Override
                public void onRun(int i) {
                    try {
                        int ping = player.getPing();
                        RePlayer rePlayer = StaticData.rePlayerMap.get(uuid);
                        int coin = rePlayer.getCoin();

                        player.sendActionBar(TextFormat.colorize("" + name + " &f延迟: " + ping + "ms 硬币: " + coin));
                    } catch (Exception e) {
                        cancel();
                    }
                }
            }, Config.JOIN_WAITING_TIME, 36);
        } catch (Exception e) {
            if (infoHandler != null)
                infoHandler.cancel();
            e.printStackTrace();
        }

    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (infoHandler != null)
            infoHandler.cancel();
    }

}
