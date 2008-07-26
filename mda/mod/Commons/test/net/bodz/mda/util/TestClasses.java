package net.bodz.mda.util;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;

public class TestClasses {

    private ClassLoader loader;

    private String[]    sections;
    private int         index;

    private String      java;
    private String      name;
    private String      expected;

    public TestClasses(ClassLoader loader, String part) throws IOException {
        this.loader = loader;
        URL testClasses = Files.classData(TestClasses.class, part);
        String def = Files.readAll(testClasses, "utf-8");
        sections = def.split("====+");
    }

    public boolean next() {
        if (index >= sections.length)
            return false;
        String section = sections[index++].trim();
        if (section.isEmpty())
            return next();

        int nl = section.indexOf('\n');
        assert nl != -1;
        String firstline = section.substring(0, nl);
        java = section.substring(nl + 1);

        int colon = firstline.indexOf(':');
        assert colon != -1;
        name = firstline.substring(0, colon).trim();
        expected = firstline.substring(colon + 1).trim();
        return true;
    }

    public String getJava() {
        return java;
    }

    public String getName() {
        return name;
    }

    public String getExpected() {
        return expected;
    }

    public String new2str(String className) throws Exception {
        Class<?> clazz = loader.loadClass(className);
        Object obj = clazz.newInstance();
        return obj.toString();
    }

}
