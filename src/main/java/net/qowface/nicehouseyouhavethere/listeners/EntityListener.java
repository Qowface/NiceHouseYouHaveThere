package net.qowface.nicehouseyouhavethere.listeners;

import net.qowface.nicehouseyouhavethere.NiceHouse;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
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

/**
 * Handles all Entity events.
 * 
 * @author Qowface
 */
public class EntityListener implements Listener {
    
    private NiceHouse plugin;
    private FileConfiguration config;
    
    public EntityListener(NiceHouse plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    
    /**
     * Prevents block damage caused by exploding Entities.
     * If the world isn't ignored and the protection is enabled in the
     * configuration, prevents all block damage.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        World world = event.getLocation().getWorld();
        
        // If world is ignored, we're done
        if (config.getList("Global Settings.Ignore Worlds").contains(world.getName())) {
            return;
        }
        
        Entity entity = event.getEntity();
        
        // If protection enabled for this entity, prevent block damage
        if (entity instanceof Creeper && config.getBoolean("Protections.Explosions.Stop Creeper Block Damage")) {
            event.blockList().clear();
        }
        else if (entity instanceof TNTPrimed && config.getBoolean("Protections.Explosions.Stop TNT Block Damage")) {
            event.blockList().clear();
        }
        else if (entity instanceof Fireball  && !(event.getEntity() instanceof WitherSkull) && config.getBoolean("Protections.Explosions.Stop Fireball Block Damage")) {
            event.blockList().clear();
        }
        else if (entity instanceof WitherSkull && config.getBoolean("Protections.Explosions.Stop Wither Skull Block Damage")) {
            event.blockList().clear();
        }
        else if (entity instanceof Wither && config.getBoolean("Protections.Explosions.Stop Wither Spawn Block Damage")) {
            event.blockList().clear();
        }
        else if (entity instanceof EnderDragon && config.getBoolean("Protections.Block Changes.Stop Ender Dragon Block Destruction")) {
            event.blockList().clear();
        }
    }
    
    /**
     * Prevents block changes caused by Entities.
     * If the world isn't ignored and the protection is enabled in the
     * configuration, prevents the block from changing.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        World world = event.getBlock().getWorld();
        
        // If world is ignored, we're done
        if (config.getList("Global Settings.Ignore Worlds").contains(world.getName())) {
            return;
        }
        
        Entity entity = event.getEntity();
        
        // If protection enabled for this entity, don't let it change the block
        if (entity instanceof Enderman && config.getBoolean("Protections.Block Changes.Stop Enderman Block Movement")) {
            event.setCancelled(true);
        }
        else if (entity instanceof Silverfish && config.getBoolean("Protections.Block Changes.Stop Silverfish From Breaking Blocks")) {
            event.setCancelled(true);
        }
        else if (entity instanceof Sheep && config.getBoolean("Protections.Block Changes.Stop Sheep From Eating Grass")) {
            event.setCancelled(true);
            // We still want it to grow wool back
            ((Sheep)event.getEntity()).setSheared(false);
        }
    }
    
}
