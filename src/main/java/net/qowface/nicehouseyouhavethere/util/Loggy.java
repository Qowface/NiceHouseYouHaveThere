package net.qowface.nicehouseyouhavethere.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;

/**
 * Handles logging and debug messages.
 * 
 * @author Qowface
 */
public class Loggy {
    
    private Plugin plugin;
    private Logger log;
    private boolean debug;
    
    public Loggy(Plugin plugin) {
        this.plugin = plugin;
        log = plugin.getLogger();
        debug = plugin.getConfig().getBoolean("Config.Debug", false);
    }
    
    public void info(String msg) {
        log.log(Level.INFO, "{0}", msg);
    }
    
    public void warn(String msg) {
        log.log(Level.WARNING, "{0}", msg);
    }
    
    public void debug(String msg) {
        if (debug) {
            log.log(Level.INFO, "[Debug] {0}", msg);
        }
    }
}
