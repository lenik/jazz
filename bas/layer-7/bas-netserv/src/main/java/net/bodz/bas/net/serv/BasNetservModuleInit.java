package net.bodz.bas.net.serv;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

import net.bodz.bas.log.log4j.ILog4jConfigurer;

public class BasNetservModuleInit
        implements ILog4jConfigurer {

    @Override
    public void setupBuilder(ConfigurationBuilder<? extends Configuration> builder) {
        builder.add(builder.newLogger("net.bodz.bas.net", Level.DEBUG));
        // builder.add(builder.newLogger("net.bodz.bas.text.parser", Level.DEBUG));
    }

}
