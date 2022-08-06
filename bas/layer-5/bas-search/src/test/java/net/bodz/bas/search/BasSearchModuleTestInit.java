package net.bodz.bas.search;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

import net.bodz.bas.log.log4j.ILog4jConfigurer;

public class BasSearchModuleTestInit
        implements
            ILog4jConfigurer {

    @Override
    public void setupBuilder(ConfigurationBuilder<? extends Configuration> builder) {
        builder.add(builder.newLogger("net.bodz", Level.DEBUG));
        builder.add(builder.newLogger("org.elasticsearch", Level.TRACE));
        builder.add(builder.newLogger("org.jboss.netty", Level.TRACE));
    }

}