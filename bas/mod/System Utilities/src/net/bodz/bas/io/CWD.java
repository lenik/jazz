package net.bodz.bas.io;

import java.io.File;

import net.bodz.bas.sys.SystemInfo;

public class CWD {

    private static final long serialVersionUID = 6765421613751651110L;

    private static File       cwd;
    static {
        String userDir = System.getProperty("user.dir");
        if (userDir == null)
            userDir = ".";
        cwd = new File(userDir);
    }

    public static boolean isAbsolutePath(String path) {
        if (path == null)
            throw new NullPointerException("path");
        if (SystemInfo.isWin32())
            path = path.replace('\\', '/');
        if (path.startsWith("/"))
            return true;
        if (SystemInfo.isWin32()) {
            if (path.length() >= 2 && path.charAt(1) == ':') {
                String diskpath = path.substring(2);
                if (diskpath.startsWith("/"))
                    return true;
            }
        }
        return false;
    }

    public static boolean isRelativePath(String path) {
        return !isAbsolutePath(path);
    }

    public static File current() {
        return cwd;
    }

    public static void chdir(File dir) {
        cwd = dir;
    }

    public static void chdir(String path) {
        if (isAbsolutePath(path))
            cwd = Files.canoniOf(path);
        else
            cwd = Files.canoniOf(cwd, path);
    }

    public static File get(String path) {
        if (isAbsolutePath(path))
            return Files.canoniOf(path);
        return Files.canoniOf(cwd, path);
    }

}
