package net.bodz.bas.sysctx;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class SystemContext
        implements ISystemContext, IWorkingDirectoryContext, ILocaleContext {

    private File workingDirectory;
    private Locale locale;

    public SystemContext() {
        File cwd = new File(".");
        try {
            workingDirectory = cwd.getCanonicalFile();
        } catch (IOException e) {
            workingDirectory = cwd.getAbsoluteFile();
        }
        locale = Locale.getDefault();
    }

    @Override
    public IWorkingDirectoryContext getWorkingDirectoryContext() {
        return this;
    }

    @Override
    public ILocaleContext getLocaleContext() {
        return this;
    }

    @Override
    public File getWorkingDirectory() {
        return workingDirectory;
    }

    @Override
    public void setWorkingDirectory(File workingDirectory) {
        if (workingDirectory == null)
            throw new NullPointerException("workingDirectory");
        this.workingDirectory = workingDirectory;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        if (locale == null)
            throw new NullPointerException("locale");
        this.locale = locale;
    }

}
