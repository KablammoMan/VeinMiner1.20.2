package dev.kablammoman.plugins.events;

import dev.kablammoman.plugins.globals.GlobalVars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

/**
 * Represents an event manager that handles block break events and implements the Listener interface.
 */
public class EventManager implements Listener {

    /**
     * Constructs an EventManager instance and registers it as an event listener with the given plugin.
     *
     * @param plugin The plugin instance to register the event listener with.
     */
    public EventManager(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Event handler method that is triggered when a block is broken by a player.
     *
     * @param event The BlockBreakEvent instance representing the block break event.
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // If vein mining is not enabled, do nothing.
        if (GlobalVars.VEIN_ENABLED == 0) return;
        // Get required data (ItemStack, Block and Material)
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        Block broken = event.getBlock();
        Material type = broken.getType();
        // If the broken block type is not in the vein mining materials list, do nothing.
        if (!GlobalVars.VEIN_MATS.contains(type)) return;
        // Get the adjacent blocks of the same type
        ArrayList<Block> toBeBroken = getAdjacentBlocksOfSameType(broken);
        for (Block b : toBeBroken) {
            // Break the adjacent blocks of the same type.
            b.breakNaturally(item);
        }
    }

    /**
     * Retrieves the adjacent blocks of the same type as the given block in a Breadth First Search (BFS) style.
     *
     * @param block The block to check for adjacent blocks of the same type.
     * @return An ArrayList of adjacent blocks of the same type.
     */
    public ArrayList<Block> getAdjacentBlocksOfSameType(Block block) {
        // Get required data from block
        Material type = block.getType();
        World blockWorld = block.getWorld();
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();
        // Initialise BFS ArrayLists
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Block> tests = new ArrayList<>();
        ArrayList<Location> checked = new ArrayList<>();
        // Add the block as the first test.
        tests.add(block);
        while (!tests.isEmpty()) {
            Block test = tests.get(0);
            int testX = test.getX();
            int testY = test.getY();
            int testZ = test.getZ();
            tests.remove(0);
            // Check if the test block is of the same type, within the break range, and if it hasn't been checked before.
            if (
                test.getType() == type &&
                Math.sqrt(Math.pow(testX - blockX, 2) + Math.pow(testY - blockY, 2) + Math.pow(testZ - blockZ, 2)) <= GlobalVars.BREAK_RANGE &&
                !checked.contains(test.getLocation())
            ) {
                blocks.add(test);
                // Add adjacent blocks to the tests list.
                tests.add(blockWorld.getBlockAt(testX + 1, testY, testZ));
                tests.add(blockWorld.getBlockAt(testX - 1, testY, testZ));
                tests.add(blockWorld.getBlockAt(testX, testY + 1, testZ));
                tests.add(blockWorld.getBlockAt(testX, testY - 1, testZ));
                tests.add(blockWorld.getBlockAt(testX, testY, testZ + 1));
                tests.add(blockWorld.getBlockAt(testX, testY, testZ - 1));
            }
            // Add current block to checked list.
            checked.add(test.getLocation());
        }
        return blocks;
    }
}