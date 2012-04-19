package net.bodz.bas.m2.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

public abstract class AbstractProjectProcessorMojo
        extends AbstractMojo {

    /**
     * The current Maven project.
     * 
     * @parameter default-value="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;

    /**
     * The Java version for which to generate source code. Default value is <code>1.5</code> for
     * plugin version 2.6+ and <code>1.4</code> in older versions.
     * 
     * @parameter expression="${jdkVersion}"
     * @since 2.4
     */
    private String jdkVersion;

    protected MavenProject getProject() {
        return project;
    }

    protected void setProject(MavenProject project) {
        this.project = project;
    }

    protected String getJdkVersion() {
        return jdkVersion;
    }

    protected void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

}
