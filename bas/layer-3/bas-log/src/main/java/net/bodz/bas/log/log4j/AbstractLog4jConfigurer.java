package net.bodz.bas.log.log4j;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggerRepository;

public abstract class AbstractLog4jConfigurer
        implements
            ILog4jConfigurer {

    @Override
    public void initLog4j(LoggerRepository hierarchy) {
    }

    void enableDebug() {
        LogLog.setInternalDebugging(true);
    }

    void reset(LoggerRepository hierarchy) {
        hierarchy.resetConfiguration();
    }

}
