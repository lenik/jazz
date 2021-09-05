package net.bodz.lily;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggerRepository;

import net.bodz.bas.log.log4j.ILog4jConfigurer;

public class LilyUberModuleTestInit
        implements
            ILog4jConfigurer {

    @Override
    public void initLog4j(LoggerRepository hierachy) {
        hierachy.getLogger("net.bodz").setLevel(Level.DEBUG);
    }

}
