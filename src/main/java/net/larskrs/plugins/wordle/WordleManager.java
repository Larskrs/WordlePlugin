package net.larskrs.plugins.wordle;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class WordleManager {

    public static HashMap<Player, WorldeGame> games;
    private Wordle main;

    public WordleManager(Wordle wordle){
        this.main = wordle;
        games = new HashMap<>();
    }
    public static void generateGame(Player player) {
        WorldeGame game = new WorldeGame(player);
        game.start();
        games.put(player, game);
    }

}
