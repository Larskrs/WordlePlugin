package net.larskrs.plugins.wordle;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;

public class WordleManager {

    public static HashMap<UUID, WorldeGame> games;
    private Wordle main;

    public WordleManager(Wordle wordle){
        this.main = wordle;
        games = new HashMap<>();
    }
    public static void generateGame(UUID uuid) {
        WorldeGame game = new WorldeGame(uuid);
        game.start();
        games.put(uuid, game);
    }

    public static boolean isPlaying(UUID uuid) {
        return games.containsKey(uuid);
    }
    public static WorldeGame getGame(UUID uuid) {
        return games.get(uuid);
    }
    public static void endGame(UUID uuid, boolean b) {
        getGame(uuid).end(b);
        games.remove(uuid);
    }

    public static String displayTextAsWordle(String s, WorldeGame game) {
        List<WLetter> Wreturns = new ArrayList<>();

        List<String> split = Arrays.asList(s.toUpperCase(Locale.ROOT).split("(?!^)"));
        List<String> splitAnswer;
        if (game == null) {
            splitAnswer = Arrays.asList(Config.getRandomWord().toUpperCase(Locale.ROOT).split("(?!^)"));
        } else {
            splitAnswer = Arrays.asList(game.getWord().toUpperCase(Locale.ROOT).split("(?!^)"));
        }


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

        StringBuilder builder = new StringBuilder();

        for (WLetter w: Wreturns
        ) {
            builder.append(ChatColor.BOLD.toString() + ChatColor.DARK_GRAY + "[" + w.succes.color + w.letter + ChatColor.BOLD.toString() + ChatColor.DARK_GRAY + "] ");
        }
        return  builder.toString();
    }
}
