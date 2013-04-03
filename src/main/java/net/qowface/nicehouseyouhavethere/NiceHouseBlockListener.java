package net.qowface.nicehouseyouhavethere;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

public class NiceHouseBlockListener implements Listener {
    
    private NiceHouse plugin;
    
    public NiceHouseBlockListener(NiceHouse plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBurn(BlockBurnEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
        if (plugin.getConfig().getBoolean("Fire.Stop Fire Spread")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
        if (event.getCause() == IgniteCause.SPREAD && plugin.getConfig().getBoolean("Fire.Stop Fire Spread")) {
            event.setCancelled(true);
        }
    }
    
}
