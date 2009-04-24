package net.bodz.bas.ant;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.nls.AppNLS;
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
        this(caller, null);
    }

    public TaskTestApp(int caller, String resourceName) throws IOException {
        this();
        Class<?> callerClass = Caller.getCallerClass(caller);
        URL url = Files.classData(callerClass);
        if ("jar".equals(url.getProtocol())) { //$NON-NLS-1$
            // if callerClass is in a jar, the default project helper is failed
            // to setBaseDir.
            File altBaseDir = SJProject.getOutputBase(callerClass);
            project.log(AppNLS.getString("TaskTestApp.altBaseDir") + altBaseDir, Project.MSG_WARN); //$NON-NLS-1$
            project.setBaseDir(altBaseDir);
        }

        load(caller + 1, resourceName);
    }

    public void load(int caller, String resourceName) throws IOException {
        Class<?> callerClass = Caller.getCallerClass(caller);
        URL xmlURL;
        if (resourceName == null)
            xmlURL = Files.classData(callerClass, "xml"); //$NON-NLS-1$
        else
            xmlURL = callerClass.getResource(resourceName);
        File buildFile = Files.getFile(xmlURL);
        if (!buildFile.exists())
            throw new IllegalUsageException(AppNLS.getString("TaskTestApp.buildFileIsntExisted") + buildFile); //$NON-NLS-1$
        load(buildFile);
    }

    public void load(File buildFile) {
        ProjectHelper.configureProject(project, buildFile);
    }

    public void run() {
        String defaultTarget = project.getDefaultTarget();
        if (defaultTarget == null)
            throw new IllegalUsageError(AppNLS.getString("TaskTestApp.noDefaultTarget") + project); //$NON-NLS-1$
        project.executeTarget(defaultTarget);
    }

    public void run(Task task) {
        task.setProject(project);
        task.execute();
    }

}
