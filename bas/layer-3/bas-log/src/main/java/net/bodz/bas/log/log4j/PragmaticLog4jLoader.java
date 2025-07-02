package net.bodz.bas.log.log4j;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Logger;

import org.apache.logging.log4j.core.config.ConfigurationFactory;

import net.bodz.bas.log.ILoggingSystemConfigurer;
import net.bodz.bas.log.diag.ILoggingSystemDebug;
import net.bodz.bas.t.order.PriorityComparator;

public class PragmaticLog4jLoader
        implements ILoggingSystemConfigurer,
                   ILoggingSystemDebug {

    static final Logger logger = Logger.getLogger(PragmaticLog4jLoader.class.getName());

    static List<ILog4jConfigurer> configurers = new ArrayList<>();

    static {
        for (ILog4jConfigurer c : ServiceLoader.load(ILog4jConfigurer.class)) {
            configurers.add(c);
        }
        configurers.sort(PriorityComparator.INSTANCE);
    }

    final boolean hasLog4j = hasLog4j();

    static boolean hasLog4j() {
        try {
            Class.forName("org.apache.logging.log4j.Logger");
            return true;
        } catch (ClassNotFoundException e) {
            logger.warning("Log4j is not available.");
            return false;
        }
    }

    @Override
    public void initLoggingSystem() {
        if (hasLog4j) {
            logger.log(LEVEL, "Log4j is available, install pragmatic config factory to log4j system .");
            ConfigurationFactory factory = new PragmaticLog4jConfigurationFactory();
            ConfigurationFactory.setConfigurationFactory(factory);
        }
    }

}
