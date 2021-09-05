package net.bodz.bas.log;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggerRepository;

import net.bodz.bas.log.log4j.ILog4jConfigurer;

public class BasLogModuleTestInit
        implements
            ILog4jConfigurer {

    @Override
    public void initLog4j(LoggerRepository hierarchy) {
        hierarchy.getLogger("net.bodz").setLevel(Level.INFO);
        hierarchy.getLogger("user").setLevel(Level.DEBUG);
    }

}