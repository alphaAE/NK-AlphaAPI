package com.alphaae.mcpe.servers.event.block.joinquit;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.scheduler.NukkitRunnable;
import com.alphaae.mcpe.servers.Config;
import com.alphaae.mcpe.servers.MainPlugin;

public class JoinWindowBlock implements JoinQuitEventBlock {

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        showJoinWindow(player);
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {

    }

    private void showJoinWindow(Player player) {
        new NukkitRunnable() {
            public void run() {
                String joinText = MainPlugin.getPlugin().getConfig().getString("join-text");
                FormWindowSimple form = new FormWindowSimple("公告", "欢迎你 " + player.getName() + " \n\n" + joinText);
                player.showFormWindow(form);
            }
        }.runTaskLater(MainPlugin.getPlugin(), Config.JOIN_WAITING_TIME);
    }
}
