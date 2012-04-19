package net.bodz.bas.c.java.io;

import java.io.File;

public class FileWhich {

    static File[] sysPaths;
    static String[] sysExts;
    static {
        String ps = System.getProperty("path.separator");
        if (ps == null)
            ps = ":";
        String pathenv = System.getenv("PATH");
        if (pathenv == null)
            sysPaths = new File[0];
        else {
            String[] v = pathenv.split(ps);
            sysPaths = new File[v.length];
            for (int i = 0; i < v.length; i++)
                sysPaths[i] = new File(v[i]);
        }

        String pathextenv = System.getenv("PATHEXT");
        if (pathextenv == null)
            sysExts = new String[0];
        else {
            String[] v = pathextenv.split(ps);
            // sysExts = new String[v.length];
            // for (int i = 0; i < v.length; i++)
            // sysExts[i] = "." + v[i];
            sysExts = v;
        }
    }

    /**
     * Find program using system default PATHEXT (win32 only).
     * 
     * @return <code>null</code> if couldn't find name.
     */
    public static File which(String name, File... paths) {
        return which(name, sysExts, paths);
    }

    /**
     * @return <code>null</code> if couldn't find name.
     */
    public static File which(String name, String[] pathExts, File... paths) {
        if (paths == null || paths.length == 0)
            paths = sysPaths;
        for (File path : paths) {
            File f = new File(path, name);
            if (f.isFile())
                return f;
            if (pathExts != null)
                for (String ext : pathExts) {
                    f = new File(path, name + ext);
                    if (f.isFile())
                        return f;
                }
        }
        return null;
    }

}
