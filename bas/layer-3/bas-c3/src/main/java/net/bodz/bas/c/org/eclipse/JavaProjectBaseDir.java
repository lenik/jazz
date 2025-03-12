package net.bodz.bas.c.org.eclipse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.err.ParseException;

public class JavaProjectBaseDir {

    public static Path fromWorkdir()
            throws ParseException {
        Path baseDir = findParents(Paths.get("."));
        if (baseDir == null)
            throw new RuntimeException("can't find the project");
        return baseDir;
    }

    public static Path findParents(Path dir) {
        if (dir != null && !Files.isDirectory(dir))
            return null;
        while (dir != null) {
            if (Files.exists(dir.resolve(".project")))
                return dir;
            dir = dir.getParent();
        }
        return null;
    }

    public static Path forClass(Class<?> clazz) {
        Path rootFile = ClassResource.getRootPath(clazz);
        return findParents(rootFile);
    }

}
