package net.larskrs.plugins.wordle;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class WorldeGame {

    private ArrayList tries;
    private String word;
    private Player player;

    public WorldeGame (Player p) {
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
            return Wreturns;
        }


    }
    public void start () {
        player.sendMessage("The game has begun, use the command /wordle try <word> to guess your way to victory.");
    }

}
