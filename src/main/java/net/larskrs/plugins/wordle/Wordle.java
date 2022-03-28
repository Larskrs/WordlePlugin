package net.larskrs.plugins.wordle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wordle extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        new Config(this);
        new WordleManager(this);

        getCommand("wordle").setExecutor(new WordleCommandExecutor(this));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
