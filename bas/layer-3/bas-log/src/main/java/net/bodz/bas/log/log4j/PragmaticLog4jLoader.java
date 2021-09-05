package net.bodz.bas.log.log4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import org.apache.log4j.LogManager;
import org.apache.log4j.spi.LoggerRepository;

import net.bodz.bas.log.ILoggingSystemConfigurer;
import net.bodz.bas.t.order.PriorityComparator;

public class PragmaticLog4jLoader
        implements
            ILoggingSystemConfigurer {

    final boolean enabled;

    public PragmaticLog4jLoader() {
        try {
            Class.forName("org.apache.log4j.Logger");
        } catch (ClassNotFoundException e) {
            enabled = false;
            return;
        }
        enabled = true;
    }

    @Override
    public void initLoggingSystem() {
        if (!enabled)
            return;

        List<ILog4jConfigurer> configurers = new ArrayList<>();
        for (ILog4jConfigurer c : ServiceLoader.load(ILog4jConfigurer.class)) {
            configurers.add(c);
        }
        Collections.sort(configurers, PriorityComparator.INSTANCE);

        LoggerRepository hierarchy = LogManager.getLoggerRepository();
        for (ILog4jConfigurer c : configurers) {
            c.initLog4j(hierarchy);
        }
    }

}
