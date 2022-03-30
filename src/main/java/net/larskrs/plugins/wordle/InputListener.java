package net.larskrs.plugins.wordle;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class InputListener implements Listener {


    @EventHandler
    public void onChat (PlayerChatEvent e) {
        if (WordleManager.isPlaying(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            Bukkit.dispatchCommand(e.getPlayer(), "wordl try " + e.getMessage());
        }
    }

}
