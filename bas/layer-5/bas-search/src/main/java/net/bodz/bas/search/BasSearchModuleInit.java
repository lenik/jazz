package net.bodz.bas.search;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggerRepository;

import net.bodz.bas.log.log4j.ILog4jConfigurer;

public class BasSearchModuleInit
        implements
            ILog4jConfigurer {

    @Override
    public void initLog4j(LoggerRepository hierarchy) {
        hierarchy.getLogger("net.bodz").setLevel(Level.INFO);
        hierarchy.getLogger("org.elasticsearch").setLevel(Level.TRACE);
        hierarchy.getLogger("org.jboss.netty").setLevel(Level.TRACE);
    }

}
