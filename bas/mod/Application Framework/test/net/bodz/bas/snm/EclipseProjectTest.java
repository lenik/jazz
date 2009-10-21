package net.bodz.bas.snm;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Test;

public class EclipseProjectTest {

    @Test
    public void testGetURLClasspath() throws Exception {
        EclipseProject ep = EclipseProject.findFromCWD();
        URL[] cp = ep.getURLClasspath();
        // 2*(app, bios, bundled, dtypes, fp, lang, sys, text, wrap)
        assertTrue(cp.length > 10);
        for (URL url : cp)
            System.out.println(url);
    }

}
