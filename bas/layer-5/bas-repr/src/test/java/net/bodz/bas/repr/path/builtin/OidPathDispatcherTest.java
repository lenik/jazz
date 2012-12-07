package net.bodz.bas.repr.path.builtin;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IncompleteDispatchException;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.TokenQueue;
import net.bodz.bas.repr.path.util.OidTree;

public class OidPathDispatcherTest
        extends Assert {

    OidTree<String> tree;

    public OidPathDispatcherTest() {
        tree = new OidTree<String>(String.class, null);
        tree.get(0).set("X");
        tree.get(1, 2).set("AB");
        tree.get(1, 2, 3).set("ABC");
        tree.get(1, 3, 4, 6, 8).set("somewhere");
    }

    @Test
    public void testFully()
            throws PathDispatchException {
        OidPathDispatcher od = new OidPathDispatcher();
        TokenQueue tq;

        Object actual = od.dispatchTest(tree, tq = new TokenQueue("0"));
        assertEquals("X", actual);
        assertEquals(0, tq.available());

        actual = od.dispatchTest(tree, "1/3/4/6/8");
        assertEquals("somewhere", actual);
    }

    @Test
    public void testOverlapped()
            throws PathDispatchException {
        OidPathDispatcher od = new OidPathDispatcher();
        TokenQueue tq;

        Object actual = od.dispatchTest(tree, tq = new TokenQueue("1/2"));
        assertEquals("AB", actual);
        assertEquals(0, tq.available());

        actual = od.dispatchTest(tree, tq = new TokenQueue("1/2/3"));
        assertEquals("ABC", actual);
        assertEquals(0, tq.available());
    }

    @Test
    public void testIncomplete()
            throws PathDispatchException {
        OidPathDispatcher od = new OidPathDispatcher();
        TokenQueue tq;

        IPathArrival result = od.dispatch(tree, tq = new TokenQueue("1/3/4"));
        assertNull(result);
        assertEquals(3, tq.available());
    }

    @Test
    public void testIncompleteOverlapped()
            throws PathDispatchException {
        OidPathDispatcher od = new OidPathDispatcher();
        TokenQueue tq;

        IPathArrival result = od.dispatch(tree, tq = new TokenQueue("1/2/4"));
        Object actual = result.getTarget();
        assertEquals("AB", actual);
        assertEquals(1, tq.available());
    }

    @Test(expected = IncompleteDispatchException.class)
    public void testIncompleteForce()
            throws PathDispatchException {
        OidPathDispatcher od = new OidPathDispatcher();
        od.dispatch(tree, ("1/3/4"));
    }

}
