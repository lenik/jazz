package net.bodz.bas.log.log4j;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface ILog4jConfigurer
        extends
            IPriority {

    void setupBuilder(ConfigurationBuilder<? extends Configuration> builder);

    default void initLog4j(Configuration config) {
    }

    default LoggerComponentBuilder logger(ConfigurationBuilder<? extends Configuration> builder, String name,
            Level level) {
        return builder.newLogger(name, level);
    }

    default LoggerComponentBuilder logger(ConfigurationBuilder<? extends Configuration> builder, String name,
            Level level, String appenderName) {
        return builder.newLogger(name, level)//
                .add(builder.newAppenderRef(appenderName));
    }

    default RootLoggerComponentBuilder rootLogger(ConfigurationBuilder<? extends Configuration> builder, String name,
            Level level) {
        return builder.newRootLogger(level);
    }

    default RootLoggerComponentBuilder rootLogger(ConfigurationBuilder<? extends Configuration> builder, Level level,
            String appenderName) {
        return builder.newRootLogger(level)//
                .add(builder.newAppenderRef(appenderName));
    }

}
