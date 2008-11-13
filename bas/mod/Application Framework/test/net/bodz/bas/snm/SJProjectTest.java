package net.bodz.bas.snm;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.snm.SJProject;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class SJProjectTest {

    private String magic = "MaGiC-GoOd..";

    @Test
    public void test1() throws IOException {
        URL src = SJProject.findSrc(SJProjectTest.class);
        String code = Files.readAll(src);
        assertEquals(1, Strings.count(code, magic));
    }

    @Test
    public void test2() throws IOException {
        String bin = Files.classData(Object.class).toString();
        String src = SJProject.findSrc(Object.class).toString();
        String src0 = bin.replace(".class", ".java");
        src0 = src0.replace("rt.jar!", "rt-source.jar!");
        assertEquals(src0, src);
    }

}
