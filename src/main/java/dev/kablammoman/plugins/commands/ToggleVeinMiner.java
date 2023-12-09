package dev.kablammoman.plugins.commands;

import dev.kablammoman.plugins.globals.GlobalVars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleVeinMiner implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            sender.sendMessage("You must specify ZERO arguments to use this command");
            return false;
        }
        GlobalVars.VEIN_ENABLED = GlobalVars.VEIN_ENABLED * -1 + 1;
        sender.sendMessage("Updated VEIN_ENABLED to " + String.valueOf(GlobalVars.VEIN_ENABLED));
        return true;
    }
}
