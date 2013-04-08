package net.qowface.nicehouseyouhavethere;

import net.qowface.nicehouseyouhavethere.listeners.BlockListener;
import net.qowface.nicehouseyouhavethere.listeners.EntityListener;
import net.qowface.nicehouseyouhavethere.util.Loggy;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NiceHouse extends JavaPlugin {
    
    public Loggy log;
    
    private EntityListener entityListener;
    private BlockListener blockListener;
    
    @Override
    public void onEnable() {
        //Initialize logger
        this.log = new Loggy(this);
        
        //Setup config
        if (getConfig().options().header() == null) {
            getConfig().options().copyHeader();
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        
        //Listeners
        PluginManager pm = this.getServer().getPluginManager();
        this.blockListener = new BlockListener(this);
        pm.registerEvents(blockListener, this);
        this.entityListener = new EntityListener(this);
        pm.registerEvents(entityListener, this);
        
        this.log.info("Enabled");
    }
    
    @Override
    public void onDisable() {
        this.log.info("Disabled");
    }
}
