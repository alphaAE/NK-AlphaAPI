package com.alphaae.mcpe.servers.event.block.joinquit;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.TextFormat;
import com.alphaae.mcpe.servers.Config;
import com.alphaae.mcpe.servers.MainPlugin;

public class DisplayInfoBlock implements JoinQuitEventBlock {

    private TaskHandler infoHandler;

    public DisplayInfoBlock() {
    }

    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        try {
            infoHandler = MainPlugin.getPlugin().getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
                @Override
                public void onRun(int i) {
                    String name = player.getName();
                    int ping = player.getPing();
                    String uuid = player.getUniqueId().toString();
                    int coin = 2000;

                    player.sendActionBar(TextFormat.colorize("&b" + name + " &f延迟: " + ping + "ms 硬币: " + coin));
                }
            }, Config.JOIN_WAITING_TIME, 12);
        } catch (Exception e) {
            infoHandler.cancel();
            e.printStackTrace();
        }

    }

    public void onPlayerQuit(PlayerQuitEvent event) {
        if (infoHandler != null)
            infoHandler.cancel();
    }

}
