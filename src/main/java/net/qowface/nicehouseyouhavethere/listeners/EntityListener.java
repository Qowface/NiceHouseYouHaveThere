package net.qowface.nicehouseyouhavethere.listeners;

import net.qowface.nicehouseyouhavethere.NiceHouse;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityListener implements Listener {
    
    private NiceHouse plugin;
    private FileConfiguration config;
    
    public EntityListener(NiceHouse plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
        //If this is a Creeper, cancel explosion block damage.
        if (event.getEntity() instanceof Creeper && config.getBoolean("Explosions.Stop Creeper Block Damage")) {
            event.blockList().clear();
        }
        //If this is TNT, cancel explosion block damage.
        else if (event.getEntity() instanceof TNTPrimed && config.getBoolean("Explosions.Stop TNT Block Damage")) {
            event.blockList().clear();
        }
        //If this is a Fireball, cancel explosion block damage.
        else if (event.getEntity() instanceof Fireball  && !(event.getEntity() instanceof WitherSkull) && config.getBoolean("Explosions.Stop Fireball Block Damage")) {
            event.blockList().clear();
        }
        //If this is a Wither Skull, cancel explosion block damage.
        else if (event.getEntity() instanceof WitherSkull && config.getBoolean("Explosions.Stop Wither Skull Block Damage")) {
            event.blockList().clear();
        }
        //If this is a Wither, cancel explosion block damage.
        else if (event.getEntity() instanceof Wither && config.getBoolean("Explosions.Stop Wither Spawn Block Damage")) {
            event.blockList().clear();
        }
        //If this is an Ender Dragon, cancel explosion block damage.
        else if (event.getEntity() instanceof EnderDragon && config.getBoolean("Block Changes.Stop Ender Dragon Block Destruction")) {
            event.blockList().clear();
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
        //If this is an Enderman, don't let it change the block.
        if (event.getEntity() instanceof Enderman && config.getBoolean("Block Changes.Stop Enderman Block Movement")) {
            event.setCancelled(true);
        }
        //If this is a Silverfish, don't let it change the block.
        else if (event.getEntity() instanceof Silverfish && config.getBoolean("Block Changes.Stop Silverfish From Breaking Blocks")) {
            event.setCancelled(true);
        }
        //If this is a Sheep, don't let it change the block.
        else if (event.getEntity() instanceof Sheep && config.getBoolean("Block Changes.Stop Sheep From Eating Grass")) {
            event.setCancelled(true);
            ((Sheep)event.getEntity()).setSheared(false);
        }
    }
    
}
