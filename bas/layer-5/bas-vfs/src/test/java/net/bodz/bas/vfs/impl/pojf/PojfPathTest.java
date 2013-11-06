package net.bodz.bas.vfs.impl.pojf;

import org.junit.Assert;
import org.junit.Test;

public class PojfPathTest
        extends Assert {

    PojfVfsDriver pojfDriver = PojfVfsDriver.getInstance();

    @Test
    public void linuxRoot() {
        PojfPath path = (PojfPath) pojfDriver.parse("/");
        assertEquals("pojf:/", path.toString());
    }

}
