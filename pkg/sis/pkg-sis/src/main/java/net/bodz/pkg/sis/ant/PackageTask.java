package net.bodz.pkg.sis.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import net.bodz.bas.ant.logger.TaskLogger;
import net.bodz.bas.c.action.IProgressMonitor;
import net.bodz.bas.err.ExceptionBuffer;
import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.bas.vfs.impl.zip.ZipVfsDevice;
import net.bodz.bas.vfs.impl.zip.ZipVfsDriver;
import net.bodz.pkg.sis.ISisProject;

/**
 * Ant task for package Distins-Project
 */
public class PackageTask
        extends Task
        implements II18nCapable {

    private ISisProject project;
    private IFile output;
    private int verboseLevel;

    public PackageTask() {
    }

    public void setProjectClass(String projectClassName)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> projectClass = Class.forName(projectClassName);
        project = (ISisProject) projectClass.newInstance();
    }

    public void setOutDir(File outdir) {
        if (outdir == null)
            throw new NullPointerException("outdir");
        output = new PojfFile(outdir);
    }

    public void setOutJar(File _zipFile) {
        if (_zipFile == null)
            throw new NullPointerException("zipFile");

        ZipVfsDriver driver = ZipVfsDriver.getInstance();
        PojfFile zipFile = new PojfFile(_zipFile);
        ZipVfsDevice device = new ZipVfsDevice(driver, zipFile, _zipFile.getName());
        output = device.getRootFile();
    }

    public int getVerboseLevel() {
        return verboseLevel;
    }

    public void setVerboseLevel(int verboseLevel) {
        this.verboseLevel = verboseLevel;
    }

    @Override
    public void execute()
            throws BuildException {
        if (project == null)
            throw new BuildException(tr._("Project isn\'t specified"));
        if (output == null)
            throw new BuildException(tr._("Output isn\'t specified"));

        TaskLogger logger = new TaskLogger(this);
        logger.setLevel(logger.getLevel(), verboseLevel);

        final List<Exception> exceptions = new ArrayList<Exception>();

        project.setArchive(output);

        IProgressMonitor monitor = null;
        IUserDialogs dialogs = ConsoleDialogs.stdout;
        IOptions options = new Options().addOption(IUserDialogs.class, dialogs);
        project.pack(monitor, options);

        // XXX monitor -> exceptions
        if (!exceptions.isEmpty()) {
            String summary = ExceptionBuffer.summary(exceptions);
            throw new BuildException(summary, exceptions.get(0));
        }
    }

}
