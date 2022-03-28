package net.larskrs.plugins.wordle;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WordleCommandExecutor implements CommandExecutor {

    private Wordle main;

    public WordleCommandExecutor(Wordle wordle) {
        main = wordle;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 && args[0].equalsIgnoreCase("play")) {
                WordleManager.generateGame(((Player) sender).getPlayer());
        } if (args.length == 2 && args[0].equalsIgnoreCase("try")) {
            if (WordleManager.games.containsKey((Player) sender)) {
                if (Config.isAWord(args[1].toUpperCase(Locale.ROOT))) {

                List<WLetter> Wreturns = WordleManager.games.get((Player) sender).checkWord(args[1]);

                StringBuilder builder = new StringBuilder();

                for (WLetter w: Wreturns
                     ) {
                    builder.append(ChatColor.BOLD.toString() + ChatColor.DARK_GRAY + "[" + w.succes.color + w.letter + ChatColor.BOLD.toString() + ChatColor.DARK_GRAY + "] ");
                }

                sender.sendMessage(builder.toString());

                } else {
                    sender.sendMessage(ChatColor.RED + "That is not a word!");
                }
            }
        }

        return false;
    }
}
