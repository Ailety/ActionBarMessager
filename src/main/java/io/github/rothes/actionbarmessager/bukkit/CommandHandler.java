package io.github.rothes.actionbarmessager.bukkit;

import io.github.rothes.actionbarmessager.bukkit.user.User;
import io.github.rothes.actionbarmessager.bukkit.user.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CommandHandler implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            switch (args[0].toUpperCase(Locale.ROOT)) {
                case "TOGGLE":
                    if (!sender.hasPermission("actionbarmessager.command.toggle")) {
                        sender.sendMessage(I18n.getPrefixedLocaledMessage("Sender.Commands.No-Permission"));
                        return true;
                    }
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(I18n.getPrefixedLocaledMessage("Sender.Commands.Player-Only-Command"));
                        return true;
                    }
                    User user = ActionBarMessager.getInstance().getUserManager().getUser((Player) sender);
                    user.setReceiveMessages(!user.isReceiveMessages());
                    return true;
                case "ON":
                    if (!sender.hasPermission("actionbarmessager.command.toggle")) {
                        sender.sendMessage(I18n.getPrefixedLocaledMessage("Sender.Commands.No-Permission"));
                        return true;
                    }
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(I18n.getPrefixedLocaledMessage("Sender.Commands.Player-Only-Command"));
                        return true;
                    }
                    User useron = ActionBarMessager.getInstance().getUserManager().getUser((Player) sender);
                    useron.setReceiveMessages(true);
                    return true;
                case "OFF":
                    if (!sender.hasPermission("actionbarmessager.command.toggle")) {
                        sender.sendMessage(I18n.getPrefixedLocaledMessage("Sender.Commands.No-Permission"));
                        return true;
                    }
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(I18n.getPrefixedLocaledMessage("Sender.Commands.Player-Only-Command"));
                        return true;
                    }
                    User useroff = ActionBarMessager.getInstance().getUserManager().getUser((Player) sender);
                    useroff.setReceiveMessages(false);
                    return true;
                case "RELOAD":
                    if (!sender.hasPermission("actionbarmessager.command.reload")) {
                        sender.sendMessage(I18n.getPrefixedLocaledMessage("Sender.Commands.No-Permission"));
                        return true;
                    }
                    ActionBarMessager.getInstance().reload();
                    ActionBarMessager.getInstance().getUserManager().updateWorldAll();
                    sender.sendMessage(I18n.getPrefixedLocaledMessage("Sender.Commands.Reload.Success"));
                    return true;
                default:
            }
        }
        sender.sendMessage(I18n.getLocaledMessage("Sender.Commands.Help.Header"));
        sender.sendMessage(I18n.getLocaledMessage("Sender.Commands.Help.Toggle"));
        sender.sendMessage(I18n.getLocaledMessage("Sender.Commands.Help.Reload"));
        sender.sendMessage(I18n.getLocaledMessage("Sender.Commands.Help.Footer"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("actionbarmessager.command.toggle")) result.add("toggle");
            if (sender.hasPermission("actionbarmessager.command.reload")) result.add("reload");
            result.add("help");
        }
        return result;
    }

}
