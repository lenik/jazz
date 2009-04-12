package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class CWDTest {

    @Test
    public void test1() throws Exception {
        File cwd = CWD.current();

        File rel = CWD.get("abc"); //$NON-NLS-1$
        assertEquals(new File(cwd, "abc"), rel); //$NON-NLS-1$

        rel = CWD.get("./def"); //$NON-NLS-1$
        assertEquals(new File(cwd, "def"), rel); //$NON-NLS-1$

        File abs = CWD.get("C:/abc"); //$NON-NLS-1$
        assertEquals(new File("C:/abc"), abs); //$NON-NLS-1$

        abs = CWD.get("C:\\abc"); //$NON-NLS-1$
        assertEquals(new File("C:\\abc"), abs); //$NON-NLS-1$
    }

}
