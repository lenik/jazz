package net.bodz.bas.c.org.eclipse;

import java.io.File;

import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.err.ParseException;

public class JavaProjectBaseDir {

    public static File fromWorkdir()
            throws ParseException {
        File baseDir = findParents(new File("."));
        if (baseDir == null)
            throw new RuntimeException("can\'t find the project");
        return baseDir;
    }

    public static File findParents(File dir) {
        if (dir != null && !dir.isDirectory())
            return null;
        while (dir != null) {
            if (new File(dir, ".project").exists())
                return dir;
            dir = dir.getParentFile();
        }
        return null;
    }

    public static File forClass(Class<?> clazz) {
        File rootFile = ClassResource.getRootFile(clazz);
        return findParents(rootFile);
    }

}
