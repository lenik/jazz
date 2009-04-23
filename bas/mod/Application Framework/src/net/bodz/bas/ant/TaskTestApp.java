package net.bodz.bas.ant;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.snm.SJProject;

import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;

/**
 * Example unit test for ant task:
 * 
 * <pre>
 * public static void main(String[] args) {
 *     new TaskTestApp().run(new YourTask());
 * }
 * </pre>
 * 
 * If you have a build-xml with same name as the test class (say, YourTask.xml),
 * you may load it with caller level=1:
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
    public BuildLogger   logger;

    public TaskTestApp() {
        project = new Project();
        project.init(); // set default taskdefs, and load sys props.

        logger = new DefaultLogger(); // AnsiColorLogger();
        logger.setOutputPrintStream(System.out);
        logger.setErrorPrintStream(System.err);
        project.addBuildListener(logger);

        logger.setMessageOutputLevel(Project.MSG_INFO);
    }

    public TaskTestApp(int caller) throws IOException {
        this();
        Class<?> callerClass = Caller.getCallerClass(caller);
        URL url = Files.classData(callerClass);
        if ("jar".equals(url.getProtocol())) {
            // if callerClass is in a jar, the default project helper is failed
            // to setBaseDir.
            File altBaseDir = SJProject.getOutputBase(callerClass);
            project.log("Using alternated base dir: " + altBaseDir, Project.MSG_WARN);
            project.setBaseDir(altBaseDir);
        }

        load(caller + 1);
    }

    public void load(int caller) throws IOException {
        Class<?> callerClass = Caller.getCallerClass(caller);
        URL xmlURL = Files.classData(callerClass, "xml");
        File buildFile = Files.getFile(xmlURL);
        if (!buildFile.exists())
            throw new IllegalUsageException("The build file for test isn't existed: " + buildFile);
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
