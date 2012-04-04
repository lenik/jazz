package lenik.lab.xjdoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectBuilder;

/**
 * A project-info plugin rewritten by Lenik.
 * 
 * @author <a href="mailto:xjl@99jsj.com">Lenik</a>
 * @goal dump
 * @phase generate-resources
 */
public class ProjectInfoMojo
        extends AbstractMojo {

    /** @parameter default-value="${project}" */
    MavenProject project;

    /** @component */
    MavenProjectBuilder projectBuilder;

    /**
     * Specifies the Antlr directory containing grammar files.
     * 
     * @parameter default-value="${basedir}/src/main/java"
     * @required
     */
    File sourceDirectory;

    /**
     * Location for generated Java files.
     * 
     * @parameter default-value="${project.build.directory}/generated-resources/project"
     * @required
     */
    File outputDirectory;

    /**
     * @parameter
     */
    File projectFile;

    public File getSourceDirectory() {
        return sourceDirectory;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException {

        File file = new File(outputDirectory, "project-info.txt");
        file.getParentFile().mkdirs();
        PrintStream out;
        try {
            out = new PrintStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }

        out.println("srcdir = " + sourceDirectory);
        out.println("outdir = " + outputDirectory);

        if (project != null) {
            for (Object _artifact : project.getDependencyArtifacts()) {
                Artifact artifact = (Artifact) _artifact;
                File artifactFile = artifact.getFile();
                out.println("dependency: " + artifactFile);
            }

            out.println("project-output: " + project.getBuild().getOutputDirectory());
            out.println("project-test-output: " + project.getBuild().getTestOutputDirectory());
        }

        out.close();
    }

}
