package net.larskrs.plugins.wordle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wordle extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        new Config(this);
        LangManager.setupLangFile(this);
        new WordleManager(this);

        WordleCommandExecutor wordleCommandExecutor = new WordleCommandExecutor(this);
        getCommand("wordle").setExecutor(wordleCommandExecutor);
        getCommand("wordle").setTabCompleter(wordleCommandExecutor);

        Bukkit.getPluginManager().registerEvents(new InputListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
