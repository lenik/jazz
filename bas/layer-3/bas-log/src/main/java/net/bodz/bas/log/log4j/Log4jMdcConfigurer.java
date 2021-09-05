package net.bodz.bas.log.log4j;

import org.apache.log4j.MDC;
import org.apache.log4j.spi.LoggerRepository;

import net.bodz.bas.log.diag.CompositeDiagContext;

public class Log4jMdcConfigurer
        implements
            ILog4jConfigurer {

    final boolean enabled;

    public Log4jMdcConfigurer() {
        try {
            Class.forName("org.apache.log4j.MDC");
        } catch (Throwable e) {
            enabled = false;
            return;
        }
        enabled = true;
    }

    @Override
    public void initLog4j(LoggerRepository hierachy) {
        if (enabled)
            MDC.put("contexts", CompositeDiagContext.CONTEXTS);
    }

}
