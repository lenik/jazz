package net.bodz.bas.c.java.nio;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t.preorder.IPreorder;

public class SubPathPreorderTest
        extends Assert {

    SubPathPreorder preorder = SubPathPreorder.INSTANCE;

    @Test
    public void testEq() {
        String a = "foo/bar";
        String b = "foo/bar";
        int result = preorder.precompare(a, b);
        assertEquals(IPreorder.EQUALS, result);
    }

    @Test
    public void testParent() {
        String a = "foo/bar/abc";
        String b = "foo/bar";
        assertEquals(IPreorder.GREATER_THAN, preorder.precompare(a, b));

        a = "foo/bar/abc";
        b = "foo/bar/";
        assertEquals(IPreorder.GREATER_THAN, preorder.precompare(a, b));
    }

    @Test
    public void testChild() {
        String a = "foo/bar";
        String b = "foo/bar/abc";
        assertEquals(IPreorder.LESS_THAN, preorder.precompare(a, b));

        a = "foo/bar/";
        b = "foo/bar/abc";
        assertEquals(IPreorder.LESS_THAN, preorder.precompare(a, b));
    }

    @Test
    public void testHintDiff() {
        String a = "foo/bar";
        String b = "foo/bar/";
        assertEquals(IPreorder.LESS_THAN, preorder.precompare(a, b));
    }

    @Test
    public void testUnknowns() {
        String a = "foo/bar";
        String b = "foo/baz";
        assertEquals(IPreorder.UNKNOWN, preorder.precompare(a, b));

        a = "foo/ba";
        b = "foo/baz";
        assertEquals(IPreorder.UNKNOWN, preorder.precompare(a, b));

        a = "foo";
        b = "/foo";
        assertEquals(IPreorder.UNKNOWN, preorder.precompare(a, b));
    }

}
