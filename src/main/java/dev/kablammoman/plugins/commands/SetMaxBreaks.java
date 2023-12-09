package dev.kablammoman.plugins.commands;

import dev.kablammoman.plugins.globals.GlobalVars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetMaxBreaks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("You must specify ONE argument to use this command");
            return false;
        }
        try {
            GlobalVars.BREAK_RANGE = Integer.parseInt(args[0]);
            sender.sendMessage("Successfully changed the range of the vein miner to " + GlobalVars.BREAK_RANGE);
        } catch (Exception e) {
            sender.sendMessage("The specified value must be an integer");
        }
        return true;
    }
}
