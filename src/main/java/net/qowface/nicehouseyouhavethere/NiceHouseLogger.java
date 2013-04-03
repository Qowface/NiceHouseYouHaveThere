package net.qowface.nicehouseyouhavethere;

import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;

public class NiceHouseLogger {
    
    private NiceHouse plugin;
    private Logger logger;
    
    public NiceHouseLogger(String logger, NiceHouse plugin) {
        this.logger = Logger.getLogger(logger);
        this.plugin = plugin;
    }
    
    private String buildString(String msg) {
        PluginDescriptionFile pdFile = plugin.getDescription();
        return "[" + pdFile.getName() + "] " + msg;
    }
    
    public void info(String msg) {
        this.logger.info(this.buildString(msg));
    }
    
    public void warn(String msg) {
        this.logger.warning(this.buildString(msg));
    }
    
    public void severe(String msg) {
        this.logger.severe(this.buildString(msg));
    }
    
}
