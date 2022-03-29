package net.larskrs.plugins.wordle;

import net.larskrs.plugins.wordle.tools.MCTextUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class WorldeGame {

    private ArrayList tries;
    private String word;
    private UUID player;

    public WorldeGame (UUID p) {
        tries = new ArrayList();
        word = Config.getRandomWord().toUpperCase();
        this.player = p;
    }

    public List<WLetter> checkWord (String attempt) {
        if (tries.size() >= Config.getMaxWorldeTries() + 1) {
            return null;
        } else {
            List<WLetter> Wreturns = new ArrayList<>();

            List<String> split = Arrays.asList(attempt.toUpperCase(Locale.ROOT).split("(?!^)"));
            List<String> splitAnswer = Arrays.asList(this.word.toUpperCase(Locale.ROOT).split("(?!^)"));



            for (int i = 0; i < splitAnswer.size(); i++) {


                String letter = split.get(i).toUpperCase();

                if (splitAnswer.contains(letter)) {
                        // the Letter is included.
                    if (splitAnswer.get(i).equalsIgnoreCase(letter))  {
                        Wreturns.add(new WLetter(WordleReturn.correct, letter));
                    } else {
                        Wreturns.add(new WLetter(WordleReturn.included, letter));
                    }
                } else {
                    Wreturns.add(new WLetter(WordleReturn.wrong, letter));
                }
            }
                Bukkit.getConsoleSender().sendMessage("The word is "+ word);

            int correct = 0;
            for (WLetter wl: Wreturns
                 ) {
                if (wl.succes == WordleReturn.correct) {
                    correct ++;
                }
            }
            
            if (correct == splitAnswer.size()) {
                WordleManager.endGame(this.player, true);
            }

            return Wreturns;
        }


    }
    public void start () {
        Player p = Bukkit.getPlayer(player);
        LangManager.sendMessage(p, LangManager.getMessageFromLocation("game-start"));
    }
    public void end (boolean won) {
        Objects.requireNonNull(Bukkit.getPlayer(player)).sendMessage(ChatColor.YELLOW + "The has ended. ");
        if (won) {
            Objects.requireNonNull(Bukkit.getPlayer(player)).sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + " YOU GOT IT! ");
            Bukkit.getPlayer(player).getWorld().playSound(Bukkit.getPlayer(player).getLocation(), Sound.ENTITY_PILLAGER_CELEBRATE, 2, 1);
            Bukkit.getPlayer(player).getWorld().playSound(Bukkit.getPlayer(player).getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
            Bukkit.getPlayer(player).getWorld().playSound(Bukkit.getPlayer(player).getLocation(), Sound.ENTITY_PARROT_AMBIENT, 1, 1);
            Bukkit.getPlayer(player).getWorld().spawnParticle(Particle.TOTEM, Bukkit.getPlayer(player).getLocation(), 300, 10);
        }
    }

}
