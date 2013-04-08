package net.qowface.nicehouseyouhavethere.listeners;

import net.qowface.nicehouseyouhavethere.NiceHouse;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

public class BlockListener implements Listener {
    
    private NiceHouse plugin;
    private FileConfiguration config;
    
    public BlockListener(NiceHouse plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBurn(BlockBurnEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
        if (config.getBoolean("Fire.Stop Fire Spread")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
        if (event.getCause() == IgniteCause.SPREAD && config.getBoolean("Fire.Stop Fire Spread")) {
            event.setCancelled(true);
        }
    }
    
}
