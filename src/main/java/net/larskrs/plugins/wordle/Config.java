package net.larskrs.plugins.wordle;

import java.util.Random;

public class Config {

    public static Wordle main;

    public Config(Wordle wordle) {
        main = wordle;
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();
    }

    public static void setMaxWorldeTries(int rows) {
        main.getConfig().set("game.settings.rows", rows);
    }
    public static int getMaxWorldeTries() {
        return main.getConfig().getInt("game.settings.rows");
    }
    public static void setWordLenght(int lenght) {
        main.getConfig().set("game.settings.lenght", lenght);
    }
    public static int getWordLenght() {
        return main.getConfig().getInt("game.settings.lenght");
    }
    public static String getRandomWord() {
        Random r = new Random();
         String s =  main.getConfig().getStringList("words").get(r.nextInt(main.getConfig().getStringList("words").size()));
        return s;
    }
}
