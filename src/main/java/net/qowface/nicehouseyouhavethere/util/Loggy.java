package net.qowface.nicehouseyouhavethere.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.qowface.nicehouseyouhavethere.NiceHouse;

public class Loggy {
    
    private NiceHouse plugin;
    private Logger log;
    
    public Loggy(NiceHouse plugin) {
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
