package net.bodz.bas.c.system;

import java.io.File;

public class SysProps {

    public static final String fileSep;
    public static final String pathSep;
    public static final File userWorkDir;
    public static final File userHome;

    public static final File dataDir;

    static {
        fileSep = SystemProperties.getFileSeparator();
        pathSep = SystemProperties.getPathSeparator();
        userWorkDir = new File(SystemProperties.getUserDir());
        userHome = new File(SystemProperties.getUserHome());

        String dataDirProperty = System.getProperty("data.dir");
        if (dataDirProperty == null)
            dataDir = new File(userHome, "data");
        else
            dataDir = new File(dataDirProperty);
    }

}
