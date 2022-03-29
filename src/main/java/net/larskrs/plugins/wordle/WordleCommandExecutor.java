package net.larskrs.plugins.wordle;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WordleCommandExecutor implements CommandExecutor, TabCompleter {

    private Wordle main;

    public WordleCommandExecutor(Wordle wordle) {
        main = wordle;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 && args[0].equalsIgnoreCase("play")) {
            WordleManager.generateGame(((Player) sender).getPlayer().getUniqueId());
        } else if (args.length == 2 && args[0].equalsIgnoreCase("addWord")) {
                if (sender.hasPermission("wordle.command.addword")) {
                Config.addWord(args[1]);
                sender.sendMessage(ChatColor.GREEN + " # You added the word + " + ChatColor.YELLOW + args[1]);
                }

        } else if (args.length == 2 && args[0].equalsIgnoreCase("try")) {
            if (WordleManager.isPlaying(((Player) sender).getUniqueId())) {
                if (Config.isAWord(args[1].toUpperCase(Locale.ROOT))) {

                List<WLetter> Wreturns = WordleManager.games.get(((Player) sender).getUniqueId()).checkWord(args[1]);

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
        } if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
            if (WordleManager.isPlaying(((Player) sender).getUniqueId())) {
                WordleManager.endGame(((Player) sender).getUniqueId(), false);
            } else {
                sender.sendMessage(ChatColor.RED + " # You are not in a game.");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] strings) {

        if (strings.length == 1) {

            List<String> list = new ArrayList<>();
            list.add("play");
            list.add("leave");
            list.add("try");
            if (sender.hasPermission("wordle.command.addword")) {
                list.add("addword");
            }
            return list;
        }
        return null;
    }
}
