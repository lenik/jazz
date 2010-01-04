package net.bodz.bas.sysinfo;

public class SystemInfo {

    public static final String pathSeparator;
    public static final String fileSeparator;
    public static final String osName;

    static {
        pathSeparator = System.getProperty("path.separator", "/"); //$NON-NLS-1$ //$NON-NLS-2$
        fileSeparator = System.getProperty("file.separator", ":"); //$NON-NLS-1$ //$NON-NLS-2$
        osName = System.getProperty("os.name", "unix"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static boolean isWin32() {
        return osName.startsWith("Windows"); //$NON-NLS-1$
    }

}
