package net.qowface.nicehouseyouhavethere.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;

public class Loggy {
    
    private Plugin plugin;
    private Logger log;
    
    public Loggy(Plugin plugin) {
        this.plugin = plugin;
        this.log = plugin.getLogger();
    }
    
    public void info(String msg) {
        log.log(Level.INFO, "{0}", msg);
    }
    
    public void warn(String msg) {
        log.log(Level.WARNING, "{0}", msg);
    }
    
}
