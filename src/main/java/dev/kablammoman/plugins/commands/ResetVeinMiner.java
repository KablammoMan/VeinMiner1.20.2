package dev.kablammoman.plugins.commands;

import dev.kablammoman.plugins.globals.GlobalVars;
import dev.kablammoman.plugins.utilities.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetVeinMiner implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            sender.sendMessage("You must specify ZERO arguments to use this command");
            return false;
        }
        if (FileManager.deleteFile("plugins/kablammoman/veinminer", true)) {
            sender.sendMessage("Successfully deleted configuration.");
            sender.sendMessage("Default configuration will be used from next server restart.");
            GlobalVars.RESET = true;
        } else {
            sender.sendMessage("Failed to delete existing configuration.");
        }
        return true;
    }
}
