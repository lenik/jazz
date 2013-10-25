package net.bodz.pkg.os.win32;

import java.io.File;

import org.junit.Assert;

public class Win32VarsTest
        extends Assert {

    public static void main(String... args)
            throws Exception {

        File value = Win32Vars.get(Win32Vars.COMMON_AUTOSTART_MENU);
        System.out.println("COMMON_AUTOSTART_MENU=" + value);
    }

}
