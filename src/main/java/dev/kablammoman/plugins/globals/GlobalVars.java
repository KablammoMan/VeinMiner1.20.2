package dev.kablammoman.plugins.globals;

import org.bukkit.Material;

import java.util.HashSet;
/**
 * A container for global variables that need to be accessed across the entire plugin and should be persistent across server restarts.
*/
public class GlobalVars {
    public static int BREAK_RANGE = 5;
    public static int VEIN_ENABLED = 0;
    public static HashSet<Material> VEIN_MATS = new HashSet<>();
    public static boolean RESET = false;
}
