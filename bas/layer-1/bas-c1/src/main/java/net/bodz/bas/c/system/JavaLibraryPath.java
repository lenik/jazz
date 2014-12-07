package net.bodz.bas.c.system;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.UnsupportedReflectionException;

public class JavaLibraryPath {

    private List<String> paths;
    private String pathSeparator = SystemProperties.getPathSeparator();

    public JavaLibraryPath() {
        this(SystemProperties.getJavaLibraryPath());
    }

    public JavaLibraryPath(String PATH) {
        String[] array = PATH.split(pathSeparator);
        this.paths = new ArrayList<String>(array.length);
        for (String path : array)
            this.paths.add(path);
    }

    public void append(String path) {
        if (path == null)
            throw new NullPointerException("path");
        paths.remove(path);
        paths.add(path);
    }

    public void prepend(String path) {
        if (path == null)
            throw new NullPointerException("path");
        paths.remove(path);
        paths.add(0, path);
    }

    public void remove(String path) {
        paths.remove(path);
    }

    public void apply() {
        String PATH = StringArray.join(pathSeparator, paths);
        System.setProperty("java.library.path", PATH);

        Field sysPathsField;
        try {
            sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);
        } catch (ReflectiveOperationException e) {
            throw new UnsupportedReflectionException(e.getMessage(), e);
        }
    }

}
