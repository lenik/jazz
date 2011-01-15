package net.bodz.bas.collection.tree;

import org.junit.Assert;
import org.junit.Test;

public class ArrayTreeNodeTest
        extends Assert {

    static Object[] array(Object... args) {
        return args;
    }

    public static final Object[] helloVar;
    public static final ArrayTreeNode helloTree;
    static {
        helloVar = array(//
                "Hello", //
                array(10, 20, 30), //
                array(40, array(41, 42)), //
                50);
        helloTree = new ArrayTreeNode(helloVar);
    }

    @Test
    public void test1()
            throws Exception {
        TreeNodes.traverse(helloTree, new TreeCallback<ArrayTreeNode>() {
            @Override
            public int each(ArrayTreeNode node, int level) {
                for (int i = 0; i < level; i++)
                    System.out.print("    "); // indent prefix.
                System.out.println(node);
                return OK;
            }
        });
    }

}
