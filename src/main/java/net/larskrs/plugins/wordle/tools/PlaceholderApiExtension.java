package net.larskrs.plugins.wordle.tools;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.larskrs.plugins.wordle.Config;
import net.larskrs.plugins.wordle.Wordle;
import net.larskrs.plugins.wordle.WordleManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PlaceholderApiExtension extends PlaceholderExpansion {

    private final Wordle plugin;

    public PlaceholderApiExtension(Wordle plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Larskrs";
    }

    @Override
    public String getIdentifier() {
        return "wordlmc";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String getVersion() {
        return "${project.version}";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {



        if(params.equalsIgnoreCase("max_tries")) {
            Bukkit.getConsoleSender().sendMessage(Config.getMaxWorldeTries() + "");
            return Config.getMaxWorldeTries() + "";
        }
        if(params.equalsIgnoreCase("current_wordl")){
            return WordleManager.getGame(player.getUniqueId()).getWord();
        }
        if (params.contains("wordl_text")) {


            Bukkit.getConsoleSender().sendMessage("we got em.");

            String[] args = StringUtils.split(params, "_");

            String output = args[2];

            return WordleManager.displayTextAsWordle(output, null);
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
