package net.larskrs.plugins.wordle;

import java.util.List;
import java.util.Locale;
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
    public static boolean isAWord (String s) {
        List<String> words = main.getConfig().getStringList("words");
        for (int i = 0; i < words.size(); i++) {
            words.set(i, words.get(i).toUpperCase(Locale.ROOT));
        }
        return words.contains(s);
    }
    public static void addWord(String word) {
        List<String> words = main.getConfig().getStringList("words");
        words.add(word);
        main.getConfig().set("words", words);

        main.saveConfig();
        main.reloadConfig();
    }

}
