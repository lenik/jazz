package net.bodz.bas.types;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.commons.collection.LinkedStack;
import net.bodz.bas.commons.tree.ArrayTreeNode;
import net.bodz.bas.commons.tree.TreePosition;

import org.junit.Test;

/**
 * @see TreePosition
 */
public class TreePositionTest {

    @Test
    public void test1() throws Exception {
        ArrayTreeNode tree = ArrayTreeNodeTest.helloTree;
        TreePosition<ArrayTreeNode> pos = new TreePosition<ArrayTreeNode>(tree, 1, 2);
        List<Object> buf = new ArrayList<Object>();
        LinkedStack<TreePosition<ArrayTreeNode>> posBuf = new LinkedStack<TreePosition<ArrayTreeNode>>();
        Iterator<ArrayTreeNode> iter = pos.iterator(null, posBuf);
        while (iter.hasNext()) {
            ArrayTreeNode succ = iter.next();
            TreePosition<ArrayTreeNode> succPos = posBuf.pop();
            System.out.println(succPos + " => " + succ); //$NON-NLS-1$
            buf.add(succ.get());
        }
        Object[] expected = { 30, 40, 41, 42, 50 };
        assertArrayEquals(expected, buf.toArray(new Object[0]));
    }

}
