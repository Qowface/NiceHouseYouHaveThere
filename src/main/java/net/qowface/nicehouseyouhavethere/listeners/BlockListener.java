package net.qowface.nicehouseyouhavethere.listeners;

import net.qowface.nicehouseyouhavethere.NiceHouse;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

/**
 * Handles all Block events.
 * 
 * @author Qowface
 */
public class BlockListener implements Listener {
    
    private NiceHouse plugin;
    private FileConfiguration config;
    
    public BlockListener(NiceHouse plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    
    /**
     * Prevents Blocks from burning.
     * If the world isn't ignored and the protection is enabled in the
     * configuration, prevents block from burning.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockBurn(BlockBurnEvent event) {
        World world = event.getBlock().getWorld();
        
        // If world is ignored, we're done
        if (config.getList("Global Settings.Ignore Worlds").contains(world.getName())) {
            return;
        }
        
        // If protection enabled, cancel the event
        if (config.getBoolean("Protections.Fire.Stop Fire Spread")) {
            event.setCancelled(true);
        }
    }
    
    /**
     * Prevents Blocks from igniting from fire spread.
     * If the world isn't ignored and the protection is enabled in the
     * configuration, prevents fire from spreading to other blocks.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockIgnite(BlockIgniteEvent event) {
        World world = event.getBlock().getWorld();
        
        // If world is ignored, we're done
        if (config.getList("Global Settings.Ignore Worlds").contains(world.getName())) {
            return;
        }
        
        // If this is fire spread and protection enabled, cancel the event
        if (event.getCause() == IgniteCause.SPREAD && config.getBoolean("Protections.Fire.Stop Fire Spread")) {
            event.setCancelled(true);
        }
    }
    
}
