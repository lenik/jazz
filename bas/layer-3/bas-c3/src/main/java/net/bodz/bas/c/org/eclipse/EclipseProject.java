package net.bodz.bas.c.org.eclipse;

import java.io.File;

public class EclipseProject {

    File baseDir;

    public EclipseProject() {
    }

    public EclipseProject(File baseDir) {
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
    }

    public File getBaseDir() {
        return baseDir;
    }

}
