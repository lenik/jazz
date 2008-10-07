package net.bodz.bas.cli.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class SNMJavaTest {

    private String magic = "MaGiC-GoOd..";

    @Test
    public void test1() throws IOException {
        URL src = SNMJava.findSrc(SNMJavaTest.class);
        String code = Files.readAll(src);
        assertEquals(1, Strings.count(code, magic));
    }

    @Test
    public void test2() throws IOException {
        String bin = Files.classData(Object.class).toString();
        String src = SNMJava.findSrc(Object.class).toString();
        String src0 = bin.replace(".class", ".java");
        src0 = src0.replace("rt.jar!", "rt-source.jar!");
        assertEquals(src0, src);
    }

}
