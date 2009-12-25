package net.bodz.bas.types;

import net.bodz.bas.commons.tree.ArrayTreeNode;
import net.bodz.bas.commons.tree.TreeCallback;
import net.bodz.bas.commons.tree.TreeNodes;
import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class ArrayTreeNodeTest {

    static Object[] array(Object... args) {
        return args;
    }

    public static final Object[] helloVar;
    public static final ArrayTreeNode helloTree;
    static {
        helloVar = array(//
                "Hello", // //$NON-NLS-1$
                array(10, 20, 30), // 
                array(40, array(41, 42)), //
                50);
        helloTree = new ArrayTreeNode(helloVar);
    }

    @Test
    public void test1() throws Exception {
        TreeNodes.traverse(helloTree, new TreeCallback<ArrayTreeNode>() {
            @Override
            public int each(ArrayTreeNode node, int level) {
                String indent = Strings.repeat(level, "    "); //$NON-NLS-1$
                System.out.println(indent + node);
                return OK;
            }
        });
    }

}
