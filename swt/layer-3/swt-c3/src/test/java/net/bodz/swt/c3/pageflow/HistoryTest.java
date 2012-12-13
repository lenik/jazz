package net.bodz.swt.c3.pageflow;

import static org.junit.Assert.*;

import org.junit.Test;

import net.bodz.bas.t.pojo.PathEntries;

public class HistoryTest {

    @Test
    public void test1()
            throws Exception {
        History history = new History(10);
        assertNull(history.get());

        history.go(new PathEntries("hello"));
        assertEquals("s1", new PathEntries("hello"), history.get());

        history.go(new PathEntries("world"));
        assertEquals("s2", new PathEntries("world"), history.get());

        assertTrue(history.has(-1));
        history.jump(-1);
        assertEquals("s1", new PathEntries("hello"), history.get());

        history.go(new PathEntries("new world"));
        assertEquals("s2", new PathEntries("new world"), history.get());

        history.go(new PathEntries("look"));
        history.jump(-1);
        System.out.println(history.toString());

        history.go(new PathEntries("truncate"));
        System.out.println(history.toString());
    }

}