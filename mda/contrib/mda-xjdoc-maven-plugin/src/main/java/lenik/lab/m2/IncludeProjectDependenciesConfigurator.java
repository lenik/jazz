package lenik.lab.m2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.m2.util.MavenLogLogger;
import net.bodz.bas.m2.util.ProjectUtils;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.Mojo;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.component.configurator.BasicComponentConfigurator;
import org.codehaus.plexus.component.configurator.ComponentConfigurationException;
import org.codehaus.plexus.component.configurator.ConfigurationListener;
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

    static Logger _logger = LoggerFactory.getLogger(IncludeProjectDependenciesConfigurator.class);
    Logger logger = _logger;

    @Override
    public synchronized void configureComponent(Object component, PlexusConfiguration configuration,
            ExpressionEvaluator expressionEvaluator, ClassRealm containerRealm, ConfigurationListener listener)
            throws ComponentConfigurationException {

        Mojo mojo = (Mojo) component;
        logger = new MavenLogLogger(mojo.getLog());

        MavenProject project = ProjectUtils.getProject(expressionEvaluator);

        PlexusConfiguration _test = configuration.getChild("testClasses");
        boolean test = _test == null ? false : ("true".equals(_test.getValue()));

        // Add project dependencies to the container realm.
        try {
            List<String> classpathElements;
            if (test)
                classpathElements = project.getTestClasspathElements();
            else
                classpathElements = project.getRuntimeClasspathElements();
            addClasspaths(containerRealm, classpathElements);
        } catch (DependencyResolutionRequiredException e) {
            throw new ComponentConfigurationException(e);
        }
        super.configureComponent(component, configuration, expressionEvaluator, containerRealm, listener);
    }

    void addClasspaths(ClassRealm classRealm, List<String> classpaths)
            throws ComponentConfigurationException {
        for (String path : classpaths) {
            URL url;
            try {
                url = new File(path).toURI().toURL();
            } catch (MalformedURLException e) {
                throw new ComponentConfigurationException("Bad classpath: " + path, e);
            }
            logger.debug("Add-Classpath: " + url);
            classRealm.addURL(url);
        }
    }

    void addArtifacts(ClassRealm classRealm, Set<Artifact> artifacts)
            throws ComponentConfigurationException {
        for (Artifact artifact : artifacts) {
            File file = artifact.getFile();
            if (file == null) {
                logger.error("Artifact.file is null: artifact");
                continue;
            }
            URL url;
            try {
                url = file.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new ComponentConfigurationException("Bad classpath: " + file, e);
            }
            logger.debug("Add-Artifact: " + file);
            classRealm.addURL(url);
        }
    }

}
