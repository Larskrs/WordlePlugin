
package net.larskrs.plugins.wordle;

public class WLetter {
    public WordleReturn succes;
    public String letter;

    public WLetter(WordleReturn ret, String letter) {
        this.succes = ret;
        this.letter = letter;
    }
}
