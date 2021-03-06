package net.bodz.bas.c.java.io;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class FileRelationTest
        extends Assert {

    void testFindBase(String expected, String a, String b) {
        File fa = a == null ? null : new File(a);
        File fb = b == null ? null : new File(b);
        File resultf = FileRelation.getCommonParentFile(fa, fb);
        String result = resultf == null ? null : resultf.toString(); // getPath();
        if (result != null)
            result = result.replace('\\', '/');
        assertEquals(expected, result);
    }

    @Test
    public void testFindBase()
            throws Exception {
        testFindBase("a/b", "a/b/c/d", "a/b/x/y");
        testFindBase("a/b", "a/b/c/d/e/f/g/h/i/j/k", "a/b/x/c/d/e/f/g/h/i/j/k");
        testFindBase("a/b", "a/b/", "a/b/x/y");
        testFindBase("a/b", "a/b", "a/b/x/y");
        testFindBase("a/b", "a/b/c/d", "a/b/");
        testFindBase("a/b", "a/b/c/d", "a/b");
        testFindBase("a", "a/b/c/d", "a");
        testFindBase(null, "a/b/c", "d/e/f");
        testFindBase("a", "a/b/../x", "a/x/"); // don't use canonical.
    }

}
