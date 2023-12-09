package dev.kablammoman.plugins.commands;

import dev.kablammoman.plugins.globals.GlobalVars;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleVeinType implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Exit if the sender did not specify enough arguments
        if (args.length == 0) {
            sender.sendMessage("You must specify at least ONE argument to use this command");
            return false;
        }
        // Loop through each argument
        for (String arg : args) {
            // Get the associated material
            Material type = Material.getMaterial(arg.toUpperCase());
            // Continue in the loop if the material name was invalid
            if (type == null) {
                sender.sendMessage(arg + " is not a valid material name.");
                continue;
            }
            // Attempt to add the material to the Set, if failed (already exists within the set) remove it.
            if (GlobalVars.VEIN_MATS.add(type)) {
                sender.sendMessage(arg + " added to vein miner list.");
            } else {
                GlobalVars.VEIN_MATS.remove(type);
                sender.sendMessage(arg + " removed from vein miner list.");
            };
        }
        return true;
    }
}
