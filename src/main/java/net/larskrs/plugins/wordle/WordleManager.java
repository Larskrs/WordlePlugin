package net.larskrs.plugins.wordle;

import java.util.HashMap;
import java.util.UUID;

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
}
