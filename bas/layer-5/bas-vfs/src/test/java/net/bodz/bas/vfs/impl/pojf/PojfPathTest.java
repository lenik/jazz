package net.bodz.bas.vfs.impl.pojf;

import org.junit.Assert;
import org.junit.Test;

public class PojfPathTest
        extends Assert {

    PojfVfsDriver driver = PojfVfsDriver.getInstance();

    @Test
    public void linuxRoot() {
        PojfPath path = (PojfPath) driver.parse("sdf/");
        assertEquals("file:/sdf/", path.toString());
    }

}
