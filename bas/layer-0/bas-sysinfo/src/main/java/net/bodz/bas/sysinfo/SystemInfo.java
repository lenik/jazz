package net.bodz.bas.sysinfo;

public class SystemInfo {

    public static final String pathSeparator;
    public static final String fileSeparator;
    public static final String osName;

    static {
        pathSeparator = System.getProperty("path.separator", "/");  
        fileSeparator = System.getProperty("file.separator", ":");  
        osName = System.getProperty("os.name", "unix");  
    }

    public static boolean isWin32() {
        return osName.startsWith("Windows"); 
    }

}
