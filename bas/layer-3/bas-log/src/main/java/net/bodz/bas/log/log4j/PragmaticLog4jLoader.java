package net.bodz.bas.log.log4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Logger;

import org.apache.logging.log4j.core.config.ConfigurationFactory;

import net.bodz.bas.log.ILoggingSystemConfigurer;
import net.bodz.bas.t.order.PriorityComparator;

public class PragmaticLog4jLoader
        implements
            ILoggingSystemConfigurer {

    static final Logger logger = Logger.getLogger(PragmaticLog4jLoader.class.getName());

    final boolean enabled;

    static List<ILog4jConfigurer> configurers = new ArrayList<>();
    static {
        for (ILog4jConfigurer c : ServiceLoader.load(ILog4jConfigurer.class)) {
            configurers.add(c);
        }
        Collections.sort(configurers, PriorityComparator.INSTANCE);
    }

    public PragmaticLog4jLoader() {
        try {
            Class.forName("org.apache.logging.log4j.Logger");
        } catch (ClassNotFoundException e) {
            logger.warning("Log4j is not available.");
            enabled = false;
            return;
        }
        enabled = true;
    }

    @Override
    public void initLoggingSystem() {
        if (!enabled)
            return;
        logger.fine("Init logging system.");
        ConfigurationFactory.setConfigurationFactory(//
                new PragmaticLog4jConfigurationFactory());
    }

}
