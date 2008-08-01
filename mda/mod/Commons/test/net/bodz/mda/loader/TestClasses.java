package net.bodz.mda.loader;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import net.bodz.bas.files.MapsFile;
import net.bodz.bas.io.Files;

public class TestClasses {

    private MapsFile                 parts;
    private Iterator<Map<String, String>> iterator;

    public TestClasses(ClassLoader loader, String part) throws IOException {
        this.loader = loader;
        URL testClasses = Files.classData(TestClasses.class, part);
        parts = new MapsFile(testClasses, "utf-8");
        iterator = parts.iterator();
    }

    private String java;
    private String name;
    private String expected;

    public boolean next() {
        if (!iterator.hasNext())
            return false;
        Map<String, String> map = iterator.next();
        java = map.get(".");
        name = map.get("name");
        expected = map.get("expected");
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

    private ClassLoader loader;

    public String new2str(String className) throws Exception {
        Class<?> clazz = loader.loadClass(className);
        Object obj = clazz.newInstance();
        return obj.toString();
    }

}
