package net.qowface.nicehouseyouhavethere;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NiceHouse extends JavaPlugin {
    
    protected NiceHouseLogger log;
    
    private NiceHouseEntityListener entityListener;
    private NiceHouseBlockListener blockListener;
    
    @Override
    public void onEnable() {
        this.log = new NiceHouseLogger("Minecraft", this);
        
        //Setup config
        if (getConfig().options().header() == null) {
            getConfig().options().copyHeader();
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        
        //Listeners
        PluginManager pm = this.getServer().getPluginManager();
        this.blockListener = new NiceHouseBlockListener(this);
        pm.registerEvents(blockListener, this);
        this.entityListener = new NiceHouseEntityListener(this);
        pm.registerEvents(entityListener, this);
        
        this.log.info("Enabled");
    }
    
    @Override
    public void onDisable() {
        this.log.info("Disabled");
    }
}
