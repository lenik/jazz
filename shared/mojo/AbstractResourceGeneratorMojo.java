package net.bodz.shared.mojo;

import java.io.File;

public abstract class AbstractResourceGeneratorMojo
        extends AbstractProjectProcessorMojo {

    /**
     * The directory to find source files, like JavaCC grammar files, or .java sources.
     *
     * @parameter property="sourceDirectory" default-value="${basedir}/src/main/generated"
     */
    private File sourceDirectory;

    /**
     * The directory to save generated files.
     *
     * @parameter property="outputDirectory"
     *            default-value="${project.build.directory}/generated-resources"
     */
    private File outputDirectory;

    /**
     * A set of Ant-like inclusion patterns used to select files from the source directory.
     *
     * @parameter
     */
    private String[] includes;

    /**
     * A set of Ant-like exclusion patterns used to prevent certain files from being processed.
     *
     * @parameter
     */
    private String[] excludes;

    public File getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(File sourceDirectory) {
        // getLog().info("Set-Source-Directory: " + sourceDirectory);
        this.sourceDirectory = sourceDirectory;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        // getLog().info("Set-Output-Directory: " + outputDirectory);
        this.outputDirectory = outputDirectory;
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

}
