package net.bodz.bas.std.misc;

import org.junit.Assert;
import org.junit.Test;

public class LicensesTest
        extends Assert {

    @Test
    public void testGetGPLv2() {
        assertTrue(Licenses.GPLv2 != null);
        assertTrue(Licenses.GPLv2.length() > 1000);
    }

    public static void main(String[] args) {
        System.out.println(Licenses.GPLv2);
    }

}
