package net.bodz.bas.sysctx;

import java.io.File;
import java.nio.file.Files;

public class WorkingDirectory {

    private static File cwd;

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
