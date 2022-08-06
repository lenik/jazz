package net.bodz.bas.log.log4j;

import java.net.URI;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

//@Plugin(name = "CustomConfigurationFactory", category = ConfigurationFactory.CATEGORY)
public class PragmaticLog4jConfigurationFactory
        extends ConfigurationFactory {

    @Override
    protected String[] getSupportedTypes() {
        return new String[] { "*" };
    }

    Configuration createConfiguration(String name, ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(Level.ERROR);

        for (ILog4jConfigurer c : PragmaticLog4jLoader.configurers) {
            c.setupBuilder(builder);
        }

        BuiltConfiguration configuration = builder.build();

        for (ILog4jConfigurer c : PragmaticLog4jLoader.configurers) {
            c.initLog4j(configuration);
        }

        return configuration;
    }

    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
        String name = source == null ? null : source.toString();
        return getConfiguration(loggerContext, name, null);
    }

    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final String name,
            final URI configLocation) {
        ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
        return createConfiguration(name, builder);
    }

}
