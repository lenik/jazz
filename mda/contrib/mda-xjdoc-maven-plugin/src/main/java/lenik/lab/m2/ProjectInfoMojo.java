package lenik.lab.m2;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

import net.bodz.bas.c.loader.ClassLoaderAnalyzer;
import net.bodz.bas.c.loader.ClassLoaderNode;
import net.bodz.bas.c.loader.ClassLoaderTreeFormatter;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.res.builtin.FileResource;

/**
 * A project-info plugin rewritten by Lenik.
 *
 * <p>
 * The usage of <b>&#64;requiresDependencyResolution</b>:
 *
 * Flags this Mojo as requiring the dependencies in the specified class path to be resolved before
 * it can execute. The matrix below illustrates which values for <i>&lt;requiredClassPath&gt;</i>
 * (first column) are supported and which dependency scopes (first row) they will request to
 * resolve:
 * <table border="0" class="bodyTable">
 * <tbody>
 * <tr class="a">
 * <td></td>
 * <td>system</td>
 * <td>provided</td>
 * <td>compile</td>
 * <td>runtime</td>
 * <td>test</td>
 * </tr>
 * <tr class="b">
 * <td><tt>compile</tt></td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * </tr>
 * <tr class="a">
 * <td><tt>runtime</tt></td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * </tr>
 * <tr class="b">
 * <td><tt>compile+runtime</tt> (since Maven 3.0)</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * </tr>
 * <tr class="a">
 * <td><tt>test</tt></td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * </tbody>
 * </table>
 * If this annotation is present but no scope is specified, the scope defaults to <tt>runtime</tt>.
 * If the annotation is not present at all, the mojo must not make any assumptions about the
 * artifacts associated with a Maven project.
 *
 * @author <a href="mailto:xjl@99jsj.com">Lenik</a>
 * @goal dump
 * @phase generate-resources
 * @requiresDependencyResolution test
 */
public class ProjectInfoMojo
        extends AbstractMojo {

    /**
     * The current Maven project.
     *
     * @parameter default-value="${project}"
     * @readonly
     * @required
     */
    MavenProject project;

    // /** @component */
    // MavenProjectBuilder projectBuilder;

    /**
     * The directory to find source files, like JavaCC grammar files, or .java sources.
     *
     * @parameter expression="${sourceDirectory}" default-value="${basedir}/src/main/java"
     */
    File sourceDirectory;

    /**
     * The directory to save generated files.
     *
     * @parameter expression="${outputDirectory}"
     *            default-value="${project.build.directory}/generated-resources/project"
     */
    File outputDirectory;

    /**
     * @parameter
     */
    File projectFile;

    ClassLoaderTreeFormatter formatter = new ClassLoaderTreeFormatter();

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException {

        File file = new File(outputDirectory, "project-info.txt");
        file.getParentFile().mkdirs();

        IPrintOut out;
        try {
            out = new FileResource(file).newPrintOut();
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }

        out.println("mojo.sourceDirectory: " + sourceDirectory);
        out.println("mojo.outputDirectory: " + outputDirectory);

        if (project != null) {

            /**
             * The dependency artifacts is only resolved since the compile phase.
             *
             * That is, "@phase" must be compile or above, or the
             * "@requiresDependencyResolution test" must be set.
             *
             * Otherwise, the dpendency artifacts is null.
             */
            Set<Artifact> dependencyArtifacts = project.getDependencyArtifacts();
            if (dependencyArtifacts == null)
                out.println("No dependency artifact.");
            else
                for (Object _artifact : dependencyArtifacts) {
                    Artifact artifact = (Artifact) _artifact;
                    File artifactFile = artifact.getFile();
                    out.println("project.dependencyArtifact: " + artifactFile);
                }

            out.println();
            out.println("project.build.outputDirectory: " + project.getBuild().getOutputDirectory());
            out.println("project.build.testOutputDirectory: " + project.getBuild().getTestOutputDirectory());

            // No such method since Maven-3.
            out.println();
            try {
                ClassRealm classRealm = project.getClassRealm();
                if (classRealm == null)
                    out.println("project.classRealm: null");
                else {
                    ClassLoaderNode realmNode = new ClassLoaderNode(null, classRealm);
                    realmNode.addTag("project.classRealm");
                    out.println("project.classRealm: ");
                    formatter.format(out, realmNode);
                }
            } catch (Throwable e) {
                out.println("Failed to get class realm: " + e.getMessage());
            }

            try {
                out.println();
                for (String classpath : project.getCompileClasspathElements())
                    out.println("Compile-Classpath: " + classpath);

                out.println();
                for (String classpath : project.getRuntimeClasspathElements())
                    out.println("Runtime-Classpath: " + classpath);

                out.println();
                for (String classpath : project.getTestClasspathElements())
                    out.println("Test-Classpath: " + classpath);
            } catch (DependencyResolutionRequiredException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        ClassLoader scl = ClassLoader.getSystemClassLoader();
        ClassLoader ccl = Thread.currentThread().getContextClassLoader();
        ClassLoaderNode[] nodes = ClassLoaderAnalyzer.mergeParents(scl, ccl);
        nodes[0].addTag("System Class Loader");
        nodes[1].addTag("Context Class Loader");

        out.println();
        out.println("Class Loader Trees:");
        Set<ClassLoaderNode> roots = ClassLoaderAnalyzer.getRoots(nodes);
        for (ClassLoaderNode root : roots)
            try {
                formatter.format(out, root);
            } catch (IOException e) {
                throw new MojoExecutionException(e.getMessage(), e);
            }

        out.close();
    }
}
