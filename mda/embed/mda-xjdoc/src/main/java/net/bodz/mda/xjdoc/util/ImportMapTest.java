package net.bodz.mda.xjdoc.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

class Foo10 {
}

public class ImportMapTest {

    ImportMap importMap;

    @Before
    public void init() {
        importMap = new ImportMap(ImportMap.class);
    }

    @Test
    public void testPrimitiveType() {
        assertEquals("int", importMap.add(int.class));
    }

    @Test
    public void testJavaLangType() {
        assertEquals("String", importMap.add(String.class));
    }

    @Test
    public void testSimpleType() {
        assertEquals("Foo10", importMap.add(Foo10.class));
    }

    @Test
    public void testArrayType() {
        Object a = new Foo10[][] {};
        String alias = importMap.add(a.getClass());
        assertEquals("Foo10[][]", alias);
    }

    public static void main(String[] args) {
        ImportMap importMap = new ImportMap(ImportMap.class);

        importMap.add(int.class);
        importMap.add(String.class);
        importMap.add(Foo10.class);
        importMap.add(IOException.class);

        for (String fqcn : importMap.getMap().values()) {
            System.out.println("%import " + fqcn);
        }
    }

}
