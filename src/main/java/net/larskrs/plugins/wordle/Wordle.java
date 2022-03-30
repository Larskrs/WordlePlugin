package net.larskrs.plugins.wordle;

import me.clip.placeholderapi.PlaceholderAPI;
import net.larskrs.plugins.wordle.tools.PlaceholderApiExtension;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class Wordle extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Wordle instance;
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    public static Wordle getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        new Config(this);
        LangManager.setupLangFile(this);
        new WordleManager(this);

        WordleCommandExecutor wordleCommandExecutor = new WordleCommandExecutor(this);
        getCommand("wordl").setExecutor(wordleCommandExecutor);
        getCommand("wordl").setTabCompleter(wordleCommandExecutor);

        new PlaceholderApiExtension(this).register();
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        //setupChat();

        Bukkit.getPluginManager().registerEvents(new InputListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }
}
