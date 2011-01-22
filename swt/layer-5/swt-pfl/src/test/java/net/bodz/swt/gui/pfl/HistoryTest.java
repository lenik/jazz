package net.bodz.swt.gui.pfl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import net.bodz.bas.types.TreePath;

import org.junit.Test;

public class HistoryTest {

    @Test
    public void test1() throws Exception {
        History history = new History(10);
        assertNull(history.get());

        history.go(new TreePath("hello"));
        assertEquals("s1", new TreePath("hello"), history.get());

        history.go(new TreePath("world"));
        assertEquals("s2", new TreePath("world"), history.get());

        assertTrue(history.has(-1));
        history.jump(-1);
        assertEquals("s1", new TreePath("hello"), history.get());

        history.go(new TreePath("new world"));
        assertEquals("s2", new TreePath("new world"), history.get());

        history.go(new TreePath("look"));
        history.jump(-1);
        System.out.println(history.toString());

        history.go(new TreePath("truncate"));
        System.out.println(history.toString());
    }

}