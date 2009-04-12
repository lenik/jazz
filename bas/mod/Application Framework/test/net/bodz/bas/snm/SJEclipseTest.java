package net.bodz.bas.snm;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Test;

public class SJEclipseTest {

    @Test
    public void test1() throws Exception {
        URL swtlib = SJEclipse.findlib("org.eclipse.swt.win32.win32.x86_"); //$NON-NLS-1$
        System.out.println(swtlib);
        assertNotNull(swtlib);
    }

}
