package net.bodz.mda.loader;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.files.PartRecords;
import net.bodz.bas.files.PartRecords.PartMap;
import net.bodz.bas.io.Files;
import net.bodz.bas.types.util.DirectIterator;

public class TestClasses {

    private PartRecords                          parts;
    private DirectIterator<PartMap, IOException> dit;

    public TestClasses(ClassLoader loader, String part) throws IOException {
        this.loader = loader;
        URL testClasses = Files.classData(TestClasses.class, part);
        parts = new PartRecords(testClasses, "utf-8");
        dit = parts.iterator();
    }

    private String java;
    private String name;
    private String expected;

    public boolean next() throws IOException {
        if (!dit.next())
            return false;
        PartMap map = dit.get();
        name = map.get("name");
        expected = map.get("expected");
        java = map.getText();
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
