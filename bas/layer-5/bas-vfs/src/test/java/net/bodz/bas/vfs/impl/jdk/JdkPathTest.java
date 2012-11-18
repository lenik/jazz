package net.bodz.bas.vfs.impl.jdk;

import org.junit.Assert;
import org.junit.Test;

public class JdkPathTest
        extends Assert {

    JdkVfsDriver driver = JdkVfsDriver.getInstance();

    @Test
    public void linuxRoot() {
        JdkPath path = (JdkPath) driver.parse("sdf/");
        assertEquals("file:/sdf/", path.toString());
    }

}
