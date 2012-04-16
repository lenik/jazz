package net.bodz.mda.xjdoc.contrib.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Builder .classdoc from Java source files.
 * 
 * @goal build
 * @phase generate-resources
 */
public class ClassDocBuilderMojo
        extends AbstractMojo {

    /**
     * The extension name used to generate classdoc resource files.
     * 
     * @parameter expression="${classdoc.extension}"
     */
    String extension = "ff";

    /**
     * Xjdoc language name.
     * 
     * @parameter expression="${classdoc.lang}"
     */
    String langName = "javadoc";

    /**
     * Include the test resources.
     * 
     * @parameter
     */
    boolean testResources;

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException {
    }

}
