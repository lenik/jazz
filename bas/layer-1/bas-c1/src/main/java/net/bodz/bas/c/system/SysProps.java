package net.bodz.bas.c.system;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SysProps {

    public static final String fileSep;
    public static final String pathSep;
    public static final Path userWorkDir;
    public static final Path userHome;

    public static final Path dataDir;

    static {
        fileSep = SystemProperties.getFileSeparator();
        pathSep = SystemProperties.getPathSeparator();
        userWorkDir = Paths.get(SystemProperties.getUserDir());
        userHome = Paths.get(SystemProperties.getUserHome());

        String dataDirProperty = System.getProperty("data.dir");
        if (dataDirProperty == null)
            dataDir = userHome.resolve("data");
        else
            dataDir = Paths.get(dataDirProperty);
    }

}
