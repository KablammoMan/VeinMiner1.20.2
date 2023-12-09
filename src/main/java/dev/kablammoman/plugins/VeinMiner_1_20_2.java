package dev.kablammoman.plugins;

import dev.kablammoman.plugins.commands.ResetVeinMiner;
import dev.kablammoman.plugins.commands.SetMaxBreaks;
import dev.kablammoman.plugins.commands.ToggleVeinMiner;
import dev.kablammoman.plugins.commands.ToggleVeinType;
import dev.kablammoman.plugins.events.EventManager;
import dev.kablammoman.plugins.globals.GlobalVars;
import dev.kablammoman.plugins.utilities.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VeinMiner_1_20_2 extends JavaPlugin {
    @Override
    // Plugin startup logic
    public void onEnable() {
        // Set appropriate command executors
        Objects.requireNonNull(getCommand("setBreakRange")).setExecutor(new SetMaxBreaks());
        Objects.requireNonNull(getCommand("toggleVeinMiner")).setExecutor(new ToggleVeinMiner());
        Objects.requireNonNull(getCommand("toggleVeinType")).setExecutor(new ToggleVeinType());
        Objects.requireNonNull(getCommand("resetVeinMiner")).setExecutor(new ResetVeinMiner());
        // Instantiate an EventManager
        new EventManager(this);
        // Using forEach to avoid Integer and Index errors if the files don't exist or are empty.
        // Read the VEIN_MATS file and for each line add the material associated to the VEIN_MATS HashSet
        FileManager.readFile("plugins/kablammoman/veinminer/VEIN_MATS.data").forEach(s -> GlobalVars.VEIN_MATS.add(Material.getMaterial(s)));
        // Read the VEIN_ENABLED file and set the VEIN_ENABLED variable to the Integer value of the contents
        FileManager.readFile("plugins/kablammoman/veinminer/VEIN_ENABLED.data").forEach(s -> GlobalVars.VEIN_ENABLED = Integer.parseInt(s));
        // Read the BREAK_RANGE file and set the BREAK_RANGE variable to the Integer value of the contents
        FileManager.readFile("plugins/kablammoman/veinminer/BREAK_RANGE.data").forEach(s -> GlobalVars.BREAK_RANGE = Integer.parseInt(s));
        // Log that the plugin has successfully initialised
        Bukkit.getLogger().info("\033[38;2;0;255;0mVein Miner Plugin Successfully Initialised\033[0m");
    }

    @Override
    // Plugin shutdown logic
    public void onDisable() {
        if (!GlobalVars.RESET) {
            // Clear the VEIN_MATS file
            FileManager.writeFile("plugins/kablammoman/veinminer","VEIN_MATS.data", "", true);
            // Write current VEIN_MATS data to VEIN_MATS file
            GlobalVars.VEIN_MATS.forEach(material -> {
                FileManager.writeFile("plugins/kablammoman/veinminer","VEIN_MATS.data", material.name() + "\n", false);
            });
            // Write current VEIN_ENABLED to VEIN_ENABLED file
            FileManager.writeFile("plugins/kablammoman/veinminer", "VEIN_ENABLED.data", String.valueOf(GlobalVars.VEIN_ENABLED), true);
            // Write current BREAK_RANGE to BREAK_RANGE file
            FileManager.writeFile("plugins/kablammoman/veinminer", "BREAK_RANGE.data", String.valueOf(GlobalVars.BREAK_RANGE), true);
        }
        // Log that the plugin has successfully shutdown.
        Bukkit.getLogger().info("\033[38;2;255;0;0mVein Miner Plugin Successfully Shut Down\033[0m");
    }
}
