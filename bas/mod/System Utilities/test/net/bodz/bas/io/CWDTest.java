package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class CWDTest {

    @Test
    public void test1() throws Exception {
        File cwd = CWD.current();

        File rel = CWD.get("abc");
        assertEquals(new File(cwd, "abc"), rel);

        rel = CWD.get("./def");
        assertEquals(new File(cwd, "def"), rel);

        File abs = CWD.get("C:/abc");
        assertEquals(new File("C:/abc"), abs);

        abs = CWD.get("C:\\abc");
        assertEquals(new File("C:\\abc"), abs);
    }

}
