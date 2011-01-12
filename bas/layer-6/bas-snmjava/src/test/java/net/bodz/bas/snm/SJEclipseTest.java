package net.bodz.bas.snm;

import java.net.URL;

import org.junit.Test;

public class SJEclipseTest {

    @Test
    public void testFindlib()
            throws Exception {
        URL swtlib = SJEclipse.findlib("org.eclipse.swt.win32.win32.x86_");
        if (swtlib != null) {
            System.out.println("Found swt library in eclipse installation: ");
            System.out.println(swtlib);
        }
    }

}
