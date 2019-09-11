package net.bodz.bas.c.system;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.string.StringArray;

public class LibraryPath {

    private List<String> paths;
    private String pathSeparator = File.pathSeparator;

    boolean hackNativeLibs = false;
    boolean useSysPaths = false;

    public LibraryPath() {
        this(SystemProperties.getJavaLibraryPath());
    }

    public LibraryPath(String PATH) {
        String[] array = PATH.split(pathSeparator);
        this.paths = new ArrayList<String>(array.length);
        for (String path : array)
            this.paths.add(path);
    }

    public LibraryPath hack(boolean useSysPaths) {
        this.hackNativeLibs = true;
        this.useSysPaths = useSysPaths;
        return this;
    }

    public void add(String path) {
        if (path == null)
            throw new NullPointerException("path");
        if (paths.add(path)) {
            applyProperty();
            if (hackNativeLibs) {
                List<String> nativePaths = getNativePaths(useSysPaths);
                if (nativePaths.add(path))
                    setNativePaths(useSysPaths, nativePaths);
            }
        }
    }

    public void remove(String path) {
        if (paths.remove(path)) {
            applyProperty();
            if (hackNativeLibs) {
                List<String> nativePaths = getNativePaths(useSysPaths);
                if (nativePaths.remove(path))
                    setNativePaths(useSysPaths, nativePaths);
            }
        }
    }

    void applyProperty() {
        String concat = StringArray.join(pathSeparator, paths);
        System.setProperty("java.library.path", concat);
    }

    static Field usrPathsField;
    static Field sysPathsField;
    static {
        try {
            usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
            sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        } catch (ReflectiveOperationException e) {
            throw new UnsupportedOperationException();
        }
        usrPathsField.setAccessible(true);
        sysPathsField.setAccessible(true);
    }

    /**
     * @param sys
     *            <code>true</code> for sys_paths, or <code>false</code> for usr_paths.
     */
    static List<String> getNativePaths(boolean sys) {
        try {
            String[] v;
            if (sys)
                v = (String[]) sysPathsField.get(null);
            else
                v = (String[]) usrPathsField.get(null);
            return new ArrayList<String>(Arrays.asList(v));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static void setNativePaths(boolean sys, List<String> list) {
        String[] v = list.toArray(new String[0]);
        try {
            if (sys)
                sysPathsField.set(null, v);
            else
                usrPathsField.set(null, v);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static LibraryPath hacked = new LibraryPath().hack(false);

    public static LibraryPath getHacked() {
        return hacked;
    }

}
