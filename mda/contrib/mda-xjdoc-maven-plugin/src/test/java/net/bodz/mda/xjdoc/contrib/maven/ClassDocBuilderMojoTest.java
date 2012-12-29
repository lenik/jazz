package net.bodz.mda.xjdoc.contrib.maven;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.bodz.bas.m2.test.MyMojoTestCase;

public class ClassDocBuilderMojoTest
        extends MyMojoTestCase {

    @Override
    protected void setUp()
            throws Exception {
        super.setUp();
    }

    @SuppressWarnings("unchecked")
    static <T> T getField(Object obj, String fieldName)
            throws ReflectiveOperationException {
        Object val = getField(obj, obj.getClass(), fieldName);
        return (T) val;
    }

    static Object getField(Object obj, Class<?> clazz, String fieldName)
            throws ReflectiveOperationException {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (NoSuchFieldException e) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass == null)
                throw e;
            else
                return getField(obj, superclass, fieldName);
        }
    }

    static void dumpTextMap(Map<?, ?> map, String prefix) {
        List<String> keys = new ArrayList<String>((Collection<? extends String>) map.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            System.out.printf("%s%s = %s\n", prefix, key, map.get(key));
        }
    }

    static void dumpTextMap2(Map<?, ?> map2) {
        List<String> keys2 = new ArrayList<String>((Collection<? extends String>) map2.keySet());
        Collections.sort(keys2);
        for (String key2 : keys2) {
            Map<?, ?> map1 = (Map<?, ?>) map2.get(key2);
            System.out.printf("%s:\n", key2);
            dumpTextMap(map1, "    ");
            System.out.println();
        }
    }

    @Test
    public void testBuildClassDocs()
            throws Exception {
        File testPom = getResourceFile("testBuildClassDocs.xml");
        assertTrue(testPom.exists());

        ClassDocBuilderMojo mojo = (ClassDocBuilderMojo) lookupMojo("build", testPom);

        assertNotNull(mojo);
        // System.out.println("Src dir = " + mojo.getSourceDirectory());
        // System.out.println("Out dir = " + mojo.getOutputDirectory());
        // System.out.println("Book Class = " + mojo.getBook().getClass());
        mojo.execute();
        // System.out.println("Done!");
    }

}
