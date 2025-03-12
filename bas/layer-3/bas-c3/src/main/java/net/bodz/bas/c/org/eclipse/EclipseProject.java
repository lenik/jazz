package net.bodz.bas.c.org.eclipse;

import java.nio.file.Path;

public class EclipseProject {

    Path baseDir;

    public EclipseProject() {
    }

    public EclipseProject(Path baseDir) {
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
    }

    public Path getBaseDir() {
        return baseDir;
    }

}
