package com.alphaae.mcpe.servers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.TextFormat;

import java.util.UUID;

public class InfoDisplay implements Listener {

    private final int WAITING_TIME = 170;

    private TaskHandler infoHandler;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        showJoinWindow(player);
//        showTestWindow(player);

        try {
            infoHandler = TestPlugin.getPlugin().getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
                @Override
                public void onRun(int i) {
                    String name = player.getName();
                    int ping = player.getPing();
                    String uuid = player.getUniqueId().toString();
                    int coin = 2000;

                    player.sendActionBar(TextFormat.colorize("&b" + name + " &f延迟: " + ping + "ms 硬币: " + coin));
                }
            }, WAITING_TIME, 12);
        } catch (Exception e) {
            infoHandler.cancel();
            e.printStackTrace();
        }

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (infoHandler != null)
            infoHandler.cancel();
    }

    private void showJoinWindow(Player player) {
        new NukkitRunnable() {
            public void run() {
                FormWindowSimple form = new FormWindowSimple("公告", "欢迎你 " + player.getName() + " \n\nQwQ，还在开发中。。。\n\n开始测试硬币系统");
                player.showFormWindow(form);
            }
        }.runTaskLater(TestPlugin.getPlugin(), WAITING_TIME);
    }

    private void showTestWindow(Player player) {
        new NukkitRunnable() {
            public void run() {
                FormWindowSimple form = new FormWindowSimple("商店", "");
                form.addButton(new ElementButton("购买", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/apple.png")));
                form.addButton(new ElementButton("购买", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/apple_golden.png")));
                form.addButton(new ElementButton("购买", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/diamond_helmet.png")));
                form.addButton(new ElementButton("购买", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/pumpkin_pie.png")));
                form.addButton(new ElementButton("购买", new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_PATH, "textures/items/melon.png")));

//                FormWindowModal formWindowModal = new FormWindowModal("协议", "猝死了要自己负责哦！", "确认", "你只能确认");
//                FormResponseModal f1 = formWindowModal.getResponse();
////                f1.getClickedButtonId();
                player.showFormWindow(form);
            }
        }.runTaskLater(TestPlugin.getPlugin(), WAITING_TIME);

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerFormResponded(PlayerFormRespondedEvent event) {
        final Player player = event.getPlayer();
        final FormResponse formResponse = event.getResponse();

//        FormResponseModal response = (FormResponseModal) formResponse;
//        player.sendMessage("" + response.getClickedButtonId());
    }


}
