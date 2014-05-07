package net.bodz.bas.t.tree;

import org.junit.Assert;
import org.junit.Test;

public class IPathInfoTest
        extends Assert {

    Node a = new Node(null, "a");
    Node b = new Node(a, "b");
    Node c = new Node(b, "c");
    Node d = new Node(c, "d");
    Node b1 = new Node(b, "b1");
    Node b2 = new Node(b1, "b2");

    @Test
    public void testAbsolute() {
        assertEquals("/a", Node.fn.getAbsolutePath(a));
        assertEquals("/a/b", Node.fn.getAbsolutePath(b));
        assertEquals("/a/b/b1", Node.fn.getAbsolutePath(b1));
        assertEquals("/a/b/b1/b2", Node.fn.getAbsolutePath(b2));
    }

    @Test
    public void testToA() {
        assertEquals(".", Node.fn.getPathFrom(a, a));
        assertEquals("b", Node.fn.getPathFrom(b, a));
        assertEquals("b/c", Node.fn.getPathFrom(c, a));
        assertEquals("b/c/d", Node.fn.getPathFrom(d, a));
    }

    @Test
    public void testToD() {
        assertEquals("../../..", Node.fn.getPathFrom(a, d));
        assertEquals("../..", Node.fn.getPathFrom(b, d));
        assertEquals("..", Node.fn.getPathFrom(c, d));
        assertEquals(".", Node.fn.getPathFrom(d, d));
        assertEquals("../../b1", Node.fn.getPathFrom(b1, d));
        assertEquals("../../b1/b2", Node.fn.getPathFrom(b2, d));
    }

}

class Node
        implements IPathInfo {

    Node parent;
    String localPath;

    public Node(Node parent, String localPath) {
        this.parent = parent;
        this.localPath = localPath;
    }

    @Override
    public IPathInfo getParent() {
        return parent;
    }

    @Override
    public String getLocalPath() {
        return localPath;
    }

}
