package net.bodz.bas.log.log4j;

import org.apache.log4j.MDC;

import net.bodz.bas.log.ILoggingSystemConfigurer;
import net.bodz.bas.log.diag.CompositeDiagContext;

public class Log4jMdcConfigurer
        implements ILoggingSystemConfigurer {

    @Override
    public void initLoggingSystem() {
        MDC.put("contexts", CompositeDiagContext.CONTEXTS);
    }

}
