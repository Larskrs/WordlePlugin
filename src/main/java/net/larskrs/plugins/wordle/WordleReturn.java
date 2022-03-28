package net.larskrs.plugins.wordle;

import org.bukkit.ChatColor;

public enum WordleReturn {
    correct(ChatColor.GREEN),
    included(ChatColor.YELLOW),
    wrong(ChatColor.GRAY);

    public ChatColor color;

    WordleReturn(ChatColor chatcolor) {
        color = chatcolor;
    }

}
