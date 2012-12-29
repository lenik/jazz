package net.bodz.bas.c.m2;

import java.io.File;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.err.UnexpectedException;

/**
 * The origin directory of a maven project.
 * 
 * So it's suggested to name as `FoobarPo`.
 */
public class MavenProjectOrigin {

    File projectDir;

    public MavenProjectOrigin(File projectDir) {
        this.projectDir = projectDir;
    }

    public File getProjectDir() {
        return projectDir;
    }

    public File find(String name) {
        return new File(projectDir, name);
    }

    public static MavenProjectOrigin fromClass(Class<?> clazz) {
        String fname = clazz.getName().replace('.', '/') + ".class";

        File classFile = ClassResource.getClassBytesFile(clazz);
        String path = classFile.getPath();
        path = FilePath.toUnixStyle(path);

        if (!path.endsWith(fname))
            throw new UnexpectedException("Unexpected class file: " + path);

        String dirname = path.substring(0, path.length() - fname.length() - 1);

        dirname = StringPart.rtrim(dirname, "target/classes");
        dirname = StringPart.rtrim(dirname, "target/test-classes");

        File dir = new File(dirname);

        return new MavenProjectOrigin(dir);
    }

    public File getSourceFile(Class<?> clazz) {
        File classFile = ClassResource.getClassBytesFile(clazz);

        // foo/target/classes/ => foo/src/main/java/
        // foo/target/test-classes/ => foo/src/test/java/
        String path = classFile.getPath();
        path = FilePath.toUnixStyle(path);
        path = path.replace("/target/classes/", "/src/main/java/");
        path = path.replace("/target/test-classes/", "/src/test/java/");

        // strip ".class"
        int lastDot = path.lastIndexOf('.');
        if (lastDot != -1)
            path = path.substring(0, lastDot);

        // strip inner classes.
        int dollar = path.indexOf('$');
        if (dollar != -1)
            path = path.substring(0, dollar);

        path += ".java";

        return new File(path);
    }

    public File getSiblingResource(File dir) {
        String path = dir.getPath();
        path = FilePath.toUnixStyle(path);
        // src/main/java => src/main/resources
        // target/classes => src/main/resources
        // target/test-classes => src/test/resources
        path = path.replaceFirst("/src/main/java", "/src/main/resources");
        path = path.replaceFirst("/target/classes", "/src/main/resources");
        path = path.replaceFirst("/target/test-classes", "/src/test/resources");
        return new File(path);
    }

    public static File getResourceDir(Class<?> clazz) {
        File classFile = ClassResource.getClassBytesFile(clazz);

        // foo/target/classes/ => foo/src/main/java/
        // foo/target/test-classes/ => foo/src/test/java/
        String path = classFile.getPath();

        int i;
        do {
            i = path.indexOf("/target/classes/");
            if (i != -1) {
                path = path.substring(0, i) + "/src/main/resources/";
                break;
            }

            i = path.indexOf("/target/test-classes/");
            if (i != -1) {
                path = path.substring(0, i) + "/src/test/resources/";
                break;
            }
        } while (false);

        return new File(path);
    }

}
