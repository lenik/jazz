package net.bodz.bas.io;

import java.io.File;

import net.bodz.bas.sys.SystemInfo;

public class CWD {

    private static final long serialVersionUID = 6765421613751651110L;

    private static File cwd;
    static {
        String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
        if (userDir == null)
            userDir = "."; //$NON-NLS-1$
        cwd = new File(userDir);
    }

    public static boolean isAbsolutePath(String path) {
        if (path == null)
            throw new NullPointerException("path"); //$NON-NLS-1$
        if (SystemInfo.isWin32())
            path = path.replace('\\', '/');
        if (path.startsWith("/")) //$NON-NLS-1$
            return true;
        if (SystemInfo.isWin32()) {
            if (path.length() >= 2 && path.charAt(1) == ':') {
                String diskpath = path.substring(2);
                if (diskpath.startsWith("/")) //$NON-NLS-1$
                    return true;
            }
        }
        return false;
    }

    public static boolean isRelativePath(String path) {
        return !isAbsolutePath(path);
    }

    public static File getcwd() {
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
