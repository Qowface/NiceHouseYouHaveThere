package net.qowface.nicehouseyouhavethere;

import net.qowface.nicehouseyouhavethere.listeners.BlockListener;
import net.qowface.nicehouseyouhavethere.listeners.EntityListener;
import net.qowface.nicehouseyouhavethere.util.Loggy;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NiceHouse extends JavaPlugin {
    
    public Loggy log;
    
    @Override
    public void onEnable() {
        // Setup config
        if (getConfig().options().header() == null) {
            getConfig().options().copyHeader();
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        
        // Initialize logger
        log = new Loggy(this);
        
        // Register event listeners
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new BlockListener(this), this);
        pm.registerEvents(new EntityListener(this), this);
        
        this.log.info("Enabled");
    }
    
    @Override
    public void onDisable() {
        this.log.info("Disabled");
    }
}
