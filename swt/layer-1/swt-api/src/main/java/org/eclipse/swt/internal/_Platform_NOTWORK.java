package org.eclipse.swt.internal;

import net.bodz.bas.c.system.SystemProperties;

/**
 * When multiple swt jars in the classpath (for various OS), this patch seems not work.
 */
public class _Platform_NOTWORK {

    public static final String PLATFORM = getPlatform();
    public static final Lock lock = new Lock();

    public static boolean isLoadable() {
        return Library.isLoadable();
    }

    static String getPlatform() {
        String osName = SystemProperties.getOsName();
        System.out.println("os name: " + osName);

        if (osName == null)
            return "gtk";

        if (osName.startsWith("Windows"))
            return "win32";

        return "gtk";
    }

    static {
        System.out.println("PLATFORM: " + PLATFORM);
    }

}
