package lenik.lab.m2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import net.bodz.bas.log.api.Logger;
import net.bodz.bas.log.api.LoggerFactory;
import net.bodz.bas.m2.util.ProjectUtils;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
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

    private static final Logger logger = LoggerFactory.getLogger(IncludeProjectDependenciesConfigurator.class);

    @Override
    public void configureComponent(Object component, PlexusConfiguration configuration,
            ExpressionEvaluator expressionEvaluator, ClassRealm containerRealm, ConfigurationListener listener)
            throws ComponentConfigurationException {

        MavenProject project = ProjectUtils.getProject(expressionEvaluator);

        // Add project dependencies to the container realm.
        try {
            List<String> runtimeClasspathElements = project.getRuntimeClasspathElements();
            // List<String> testClasspathElements = project.getTestClasspathElements();
            Set<Artifact> artifacts = project.getArtifacts();

            addClasspaths(containerRealm, runtimeClasspathElements);
            addArtifacts(containerRealm, artifacts);
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
            logger.info("Add-Classpath: ", url);
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
            logger.info("Add-Artifact: ", file);
            classRealm.addURL(url);
        }
    }

}
