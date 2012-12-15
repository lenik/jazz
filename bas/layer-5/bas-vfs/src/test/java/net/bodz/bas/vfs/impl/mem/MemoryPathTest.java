package net.bodz.bas.vfs.impl.mem;

import org.junit.Assert;
import org.junit.Test;

public class MemoryPathTest
        extends Assert {

    @Test
    public void testCreatePath() {
        MemoryPath path = new MemoryPath("mem", "a", "f/");
        System.out.println(path);

        System.out.println(path.join("//apple/////"));
    }

}
