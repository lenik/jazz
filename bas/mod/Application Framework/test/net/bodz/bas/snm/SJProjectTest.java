package net.bodz.bas.snm;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.bodz.bas.cli.BasicCLI;
import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class SJProjectTest {

    private String magic = "MaGiC-GoOd.."; //$NON-NLS-1$

    File           projectBase;

    public SJProjectTest() {
        projectBase = SJProject.findProjectBase(SJProjectTest.class);
    }

    @Test
    public void testFindProjectBase() throws Exception {
        System.out.println("[projbase] " + projectBase); //$NON-NLS-1$
    }

    @Test
    public void testGetOutputBase() throws Exception {
        File obSelf = SJProject.getOutputBase(SJProjectTest.class);
        assertEquals(new File(projectBase, "mod/Application Framework/test.bin"), obSelf); //$NON-NLS-1$
        File obRt = SJProject.getOutputBase(Object.class);
        System.out.println("[outbase] Object.class => " + obRt); //$NON-NLS-1$
    }

    @Test
    public void testGetResBase() throws Exception {
        ResFolder rbSelf = SJProject.getResBase(SJProjectTest.class);
        assertEquals(
                new FileResFolder(new File(projectBase, "mod/Application Framework/test.bin")), //$NON-NLS-1$
                rbSelf);
        ResFolder rbRt = SJProject.getResBase(Object.class);
        System.out.println("[resbase] Object.class => " + rbRt); //$NON-NLS-1$
    }

    @Test
    public void testGetSrcURL_findMagic() throws IOException {
        URL src = SJProject.getSrcURL(SJProjectTest.class);
        String code = Files.readAll(src);
        assertEquals(1, Strings.count(code, magic));
    }

    @Test
    public void testGetSrcURL_rtjar() throws IOException {
        String classRes = Files.classData(Object.class).toString();
        String srcRes = SJProject.getSrcURL(Object.class).toString();
        String srcExpected = classRes.replace(".class", ".java"); //$NON-NLS-1$ //$NON-NLS-2$
        srcExpected = srcExpected.replace("rt.jar!", "rt-src.jar!"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(srcExpected, srcRes);
    }

    @Test
    public void testGetSrcURLWithName() throws IOException {
        URL url = SJProject.getSrcURLWithName(BasicCLI.class, "a/Option.java"); //$NON-NLS-1$
        System.out.println("[src/name] (BasicCLI, a/Option) => " + url); //$NON-NLS-1$
    }

}
