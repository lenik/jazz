package lenik.lab.m2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.bodz.bas.log.api.Logger;
import net.bodz.bas.log.api.LoggerFactory;

import org.codehaus.classworlds.ClassRealm;
import org.codehaus.plexus.component.configurator.BasicComponentConfigurator;
import org.codehaus.plexus.component.configurator.ComponentConfigurationException;
import org.codehaus.plexus.component.configurator.ConfigurationListener;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluator;
import org.codehaus.plexus.configuration.PlexusConfiguration;

/**
 * Add the project's runtime classpath to plugin classpath.
 * 
 * @plexus.component role="org.codehaus.plexus.component.configurator.ComponentConfigurator"
 *                   role-hint="include-project-dependencies"
 * @plexus.requirement 
 *                     role="org.codehaus.plexus.component.configurator.converters.lookup.ConverterLookup"
 *                     role-hint="default"
 */
public class IncludeProjectDependenciesConfigurator
        extends BasicComponentConfigurator {

    private static final Logger logger = LoggerFactory.getLogger(IncludeProjectDependenciesConfigurator.class);

    @Override
    public void configureComponent(Object component, PlexusConfiguration configuration,
            ExpressionEvaluator expressionEvaluator, ClassRealm containerRealm, ConfigurationListener listener)
            throws ComponentConfigurationException {

        // Add project dependencies to the container realm.
        List<String> runtimeClasspathElements;
        try {
            runtimeClasspathElements = (List<String>) expressionEvaluator
                    .evaluate("${project.runtimeClasspathElements}");
        } catch (ExpressionEvaluationException e) {
            throw new ComponentConfigurationException(
                    "Failed to evaluate expression: ${project.runtimeClasspathElements}", e);
        }
        for (String path : runtimeClasspathElements) {
            URL url;
            try {
                url = new File(path).toURI().toURL();
            } catch (MalformedURLException e) {
                throw new ComponentConfigurationException("Bad classpath: " + path, e);
            }
            logger.info("Add-Constituent: ", url);
            containerRealm.addConstituent(url);
        }

        super.configureComponent(component, configuration, expressionEvaluator, containerRealm, listener);
    }

}
