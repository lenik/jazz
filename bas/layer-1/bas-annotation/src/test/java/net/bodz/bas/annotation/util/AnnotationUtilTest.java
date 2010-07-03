package net.bodz.bas.annotation.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.Test;

public class AnnotationUtilTest {

    @Retention(RetentionPolicy.RUNTIME)
    static @interface ExampleAnnotation {
    }

    @ExampleAnnotation
    static class ClassA {
    }

    static class ClassA1
            extends ClassA {
    }

    @Test
    public void testGetDeclaredAnnotation_Declared() {
        ExampleAnnotation _a = AnnotationUtil.getDeclaredAnnotation(ClassA.class, ExampleAnnotation.class);
        assertNotNull(_a);
    }

    @Test
    public void testGetDeclaredAnnotation_NotDeclared() {
        ExampleAnnotation _a = AnnotationUtil.getDeclaredAnnotation(ClassA1.class, ExampleAnnotation.class);
        assertNull(_a);
    }

}
