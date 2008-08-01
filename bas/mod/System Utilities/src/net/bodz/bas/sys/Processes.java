package net.bodz.bas.sys;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.types.util.Arrays2;
import net.bodz.bas.types.util.Strings;

public class Processes {

    private static String[] shvec;
    private static String   shprefix;
    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            String comspec = System.getenv("COMSPEC");
            if (comspec == null)
                comspec = "cmd";
            shvec = new String[] { comspec, "/c" };
        } else {
            String shell = System.getenv("SHELL");
            if (shell == null)
                shell = "sh";
            shvec = new String[] { shell };
        }
        shprefix = Strings.join(" ", shvec) + " ";
    }

    public static Process shellExec(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command);
    }

    public static Process shellExec(String command, String[] envp)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command, envp);
    }

    public static Process shellExec(String command, String[] envp, File dir)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command, envp, dir);
    }

    public static Process shellExec(String[] cmdarray) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(Arrays2._concat(shvec, cmdarray));
    }

    public static Process shellExec(String[] cmdarray, String[] envp)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(Arrays2._concat(shvec, cmdarray), envp);
    }

    public static Process exec(String[] cmdarray, String[] envp, File dir)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(Arrays2._concat(shvec, cmdarray), envp, dir);
    }

}
