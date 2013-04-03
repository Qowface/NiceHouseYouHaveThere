package net.qowface.nicehouseyouhavethere;

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

public class NiceHouseEntityListener implements Listener {
    
    private NiceHouse plugin;
    
    public NiceHouseEntityListener(NiceHouse plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
        //If this is a Creeper, cancel explosion block damage.
        if (event.getEntity() instanceof Creeper && plugin.getConfig().getBoolean("Explosions.Stop Creeper Block Damage")) {
            event.blockList().clear();
        }
        //If this is TNT, cancel explosion block damage.
        else if (event.getEntity() instanceof TNTPrimed && plugin.getConfig().getBoolean("Explosions.Stop TNT Block Damage")) {
            event.blockList().clear();
        }
        //If this is a Fireball, cancel explosion block damage.
        else if (event.getEntity() instanceof Fireball  && !(event.getEntity() instanceof WitherSkull) && plugin.getConfig().getBoolean("Explosions.Stop Fireball Block Damage")) {
            event.blockList().clear();
        }
        //If this is a Wither Skull, cancel explosion block damage.
        else if (event.getEntity() instanceof WitherSkull && plugin.getConfig().getBoolean("Explosions.Stop Wither Skull Block Damage")) {
            event.blockList().clear();
        }
        //If this is a Wither, cancel explosion block damage.
        else if (event.getEntity() instanceof Wither && plugin.getConfig().getBoolean("Explosions.Stop Wither Spawn Block Damage")) {
            event.blockList().clear();
        }
        //If this is an Ender Dragon, cancel explosion block damage.
        else if (event.getEntity() instanceof EnderDragon && plugin.getConfig().getBoolean("Block Changes.Stop Ender Dragon Block Destruction")) {
            event.blockList().clear();
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
        //If this is an Enderman, don't let it change the block.
        if (event.getEntity() instanceof Enderman && plugin.getConfig().getBoolean("Block Changes.Stop Enderman Block Movement")) {
            event.setCancelled(true);
        }
        //If this is a Silverfish, don't let it change the block.
        else if (event.getEntity() instanceof Silverfish && plugin.getConfig().getBoolean("Block Changes.Stop Silverfish From Breaking Blocks")) {
            event.setCancelled(true);
        }
        //If this is a Sheep, don't let it change the block.
        else if (event.getEntity() instanceof Sheep && plugin.getConfig().getBoolean("Block Changes.Stop Sheep From Eating Grass")) {
            event.setCancelled(true);
            ((Sheep)event.getEntity()).setSheared(false);
        }
    }
    
}
