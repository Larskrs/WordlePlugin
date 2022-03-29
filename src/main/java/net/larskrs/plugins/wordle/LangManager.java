package net.larskrs.plugins.wordle;

import net.larskrs.plugins.wordle.objects.StringReplacement;
import net.larskrs.plugins.wordle.tools.MCTextUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class LangManager {

    private static YamlConfiguration lang;

    public static void setupLangFile(Wordle wordle) {
        File file = new File(wordle.getDataFolder(), "lang.yml");
        if (!file.exists()) {
            wordle.saveResource("lang.yml", false);
        }
        lang = YamlConfiguration.loadConfiguration(file);
    }

    public static List<String> getMessageFromLocation(String location) {
        return lang.getStringList(location);
    }
    public static void sendMessage(Player p, List<String> m) {
        for (int i = 0; i <m.size(); i++) {

            String cen = "<Center>";

            boolean isFound = m.get(i).contains(cen);


            if (isFound) {

                String ne = StringUtils.remove(m.get(i), cen);
                MCTextUtil.sendCenteredMessage(p, ChatColor.translateAlternateColorCodes('&', ne));
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', m.get(i)));
            }
        }
    }
    public static void sendMessage(List<Player> pl, List<String> m) {
        for (int p = 0; p < pl.size(); p++) {
            for (int i = 0; i <m.size(); i++) {
                pl.get(p).sendMessage(ChatColor.translateAlternateColorCodes('&', m.get(i)));
            }
        }
    }
    public static String getReplacements (List<String> list){
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < StringReplacement.values().length; j++) {

            }
        }
        return null;
    }


}
