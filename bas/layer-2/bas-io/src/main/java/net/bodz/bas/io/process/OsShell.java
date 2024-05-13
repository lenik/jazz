package net.bodz.bas.io.process;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.string.StringArray;

public class OsShell {

    public static final List<String> shellCmdv;
    public static final String shellCmdPrefix;
    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            String comspec = System.getenv("COMSPEC");
            if (comspec == null)
                comspec = "cmd";
            shellCmdv = Arrays.asList(comspec, "/c");
        } else {
            String shell = System.getenv("SHELL");
            if (shell == null)
                shell = "sh";
            shellCmdv = Arrays.asList(shell, "-c");
            // shvec = new String[] { shell };
        }
        shellCmdPrefix = StringArray.join(" ", shellCmdv);
    }

}
