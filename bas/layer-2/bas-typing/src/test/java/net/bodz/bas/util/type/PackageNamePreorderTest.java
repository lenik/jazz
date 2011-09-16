package net.bodz.bas.util.type;

import net.bodz.bas.collection.preorder.IPreorder;
import net.bodz.bas.collection.preorder.PackageNamePreorder;

import org.junit.Assert;
import org.junit.Test;

public class PackageNamePreorderTest
        extends Assert {

    PackageNamePreorder preorder = PackageNamePreorder.getInstance();

    @Test
    public void testSamples()
            throws Exception {
        assertEquals(IPreorder.EQUALS, preorder.precompare("a", "a"));
        assertEquals(IPreorder.EQUALS, preorder.precompare("a.b", "a.b"));

        assertEquals(IPreorder.GREATER_THAN, preorder.precompare("com.example.a", "com.example"));
        assertEquals(IPreorder.LESS_THAN, preorder.precompare("com.example", "com.example.a"));
        assertEquals(IPreorder.LESS_THAN, preorder.precompare("com.example", "com.example."));
    }

}
