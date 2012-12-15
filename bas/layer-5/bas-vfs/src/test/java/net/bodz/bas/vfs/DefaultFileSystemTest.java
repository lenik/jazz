package net.bodz.bas.vfs;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DefaultFileSystemTest
        extends Assert {

    @Test
    public void testListProviders()
            throws Exception {
        DefaultFileSystem fs = new DefaultFileSystem();
        Map<String, IVfsDriver> map = fs.getDriverMap();
        System.out.println(map);
    }

}
