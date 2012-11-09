package net.bodz.bas.ant;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.snm.JarLocations;

/**
 * Example unit test for ant task:
 * 
 * <pre>
 * public static void main(String[] args) {
 *     new TaskTestApp().run(new YourTask());
 * }
 * </pre>
 * 
 * If you have a build-xml with same name as the test class (say, YourTask.xml), you may load it
 * with caller level=1:
 * 
 * <pre>
 * class YourTask {
 *     public static void main(String[] args) {
 *         new TaskTestApp(1).run();
 *     }
 * }
 * </pre>
 * 
 */
public class TaskTestApp {

    public final Project project;
    public BuildLogger logger;

    public TaskTestApp() {
        project = new Project();
        project.init(); // set default taskdefs, and load sys props.

        logger = new DefaultLogger(); // AnsiColorLogger();
        logger.setOutputPrintStream(System.out);
        logger.setErrorPrintStream(System.err);
        project.addBuildListener(logger);

        logger.setMessageOutputLevel(Project.MSG_INFO);
    }

    public TaskTestApp(int caller)
            throws IOException {
        this(caller + 1, null);
    }

    public TaskTestApp(int caller, String resourceName)
            throws IOException {
        this();
        Class<?> callerClass = Caller.getCallerClass(caller);
        URL url = ClassResource.getDataURL(callerClass);
        if ("jar".equals(url.getProtocol())) {
            // if callerClass is in a jar, the default project helper is failed
            // to setBaseDir.
            File altBaseDir = JarLocations.getBaseClasspath(callerClass);
            project.log("Using alternated base dir: " + altBaseDir, Project.MSG_WARN);
            project.setBaseDir(altBaseDir);
        }

        load(caller + 1, resourceName);
    }

    public void load(int caller, String resourceName)
            throws IOException {
        Class<?> callerClass = Caller.getCallerClass(caller);
        URL xmlURL;
        if (resourceName == null)
            xmlURL = ClassResource.getDataURL(callerClass, "xml");
        else
            xmlURL = callerClass.getResource(resourceName);
        File buildFile = FileURL.getFile(xmlURL); // Must be a File, not resource in zip.
        if (!buildFile.exists())
            throw new IllegalUsageException("The build file for test isn\'t existed: " + buildFile);
        load(buildFile);
    }

    public void load(File buildFile) {
        ProjectHelper.configureProject(project, buildFile);
    }

    public void run() {
        String defaultTarget = project.getDefaultTarget();
        if (defaultTarget == null)
            throw new IllegalUsageError("No default target in the project " + project);
        project.executeTarget(defaultTarget);
    }

    public void run(Task task) {
        task.setProject(project);
        task.execute();
    }

}
