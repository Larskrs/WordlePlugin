package net.larskrs.plugins.wordle;

import jdk.internal.net.http.hpack.HPACK;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.*;
import java.util.logging.Level;

public class WorldeGame {

    private int tries;
    private String word;
    private UUID player;
    private Wordle main = Wordle.getInstance();

    public WorldeGame (UUID p) {
        tries = Config.getMaxWorldeTries();
        word = Config.getRandomWord().toUpperCase();
        this.player = p;
    }

    public List<WLetter> checkWord (String attempt) {

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
            } else if (tries == 0)  {
                Bukkit.getConsoleSender().sendMessage("dude should be dead.");
                WordleManager.endGame(this.player, false);
            }
            tries -= 1;
            return Wreturns;
    }
    public String getWord () {
        return word;
    }
    public void start () {
        Player p = Bukkit.getPlayer(player);
        LangManager.sendMessage(p, LangManager.getMessageFromLocation("game-start"));
    }
    public void end (boolean won) {

        if (won) {

            Player p = Bukkit.getPlayer(player);

            LangManager.sendMessage(p, LangManager.getMessageFromLocation("game-end-win"));
            p.getWorld().playSound(Bukkit.getPlayer(player).getLocation(), Sound.ENTITY_PILLAGER_CELEBRATE, 2, 1);
            p.getWorld().playSound(Bukkit.getPlayer(player).getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
            p.getWorld().playSound(Bukkit.getPlayer(player).getLocation(), Sound.ENTITY_PARROT_AMBIENT, 1, 1);
            for (String rewardCMD: Config.getRewardCommand()
                 ) {
                rewardCMD = rewardCMD.replace("%player%", p.getName());

                if (rewardCMD.startsWith("CONSOLE: ")) {
                    String plC = PlaceholderAPI.setPlaceholders(p, rewardCMD.replace("CONSOLE: ", ""));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plC);
                } else if (rewardCMD.startsWith("PLAYER: ")) {
                    String plC = PlaceholderAPI.setPlaceholders(p, rewardCMD.replace("PLAYER: ", ""));
                    boolean wasOp = p.isOp();
                    p.setOp(true);
                    Bukkit.getServer().dispatchCommand(p, plC);
                    p.setOp(wasOp);
                }
            }
            //Bukkit.getPlayer(player).getWorld().spawnParticle(Particle.TOTEM, Bukkit.getPlayer(player).getLocation(), 300, 10);
        } else {
            LangManager.sendMessage(Bukkit.getPlayer(player), LangManager.getMessageFromLocation("game-end-loss"));
        }
    }

}
