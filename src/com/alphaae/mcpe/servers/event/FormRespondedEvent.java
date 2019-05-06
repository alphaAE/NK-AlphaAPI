package com.alphaae.mcpe.servers.event;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindow;
import com.alphaae.mcpe.servers.event.block.form.FormEvent;

public class FormRespondedEvent implements Listener {

    //窗口返回事件
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerFormResponded(PlayerFormRespondedEvent event) {
        FormWindow window = event.getWindow();
        if (event.getResponse() == null) return;
        if (window instanceof FormEvent)
            ((FormEvent) window).RespondedEvent(event);

    }

}
