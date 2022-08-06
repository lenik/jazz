package net.bodz.bas.log.log4j;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

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
    public void setupBuilder(ConfigurationBuilder<? extends Configuration> builder) {
        if (enabled)
            ; // TODO MDC.put("contexts", CompositeDiagContext.CONTEXTS);
    }

}
