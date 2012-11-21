package net.bodz.jna.win32;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class NetApi32Test
        extends Assert
        implements NetApi32 {

    @Before
    public void checkWin32() {
        Assume.assumeTrue(Win32Config.isWin32());
    }

    @Test
    public void test1() {

    }

}
