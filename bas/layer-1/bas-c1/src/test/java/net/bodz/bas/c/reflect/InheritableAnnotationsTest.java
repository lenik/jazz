package net.bodz.bas.c.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @see InheritableAnnotations
 */
public class InheritableAnnotationsTest
        extends Assert {

    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface Foo {
        String value() default "";
    }

    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface Bar {
        String value() default "";
    }

    @Foo("A")
    class A {

        @Foo("A::fun")
        public void fun() {
        }

    }

    @Bar("B")
    class B
            extends A {

        @Foo("B::fun")
        @Bar("B::fun")
        @Override
        public void fun() {
        }

    }

    @Test
    public void getDeclaredAnnotations() {
        List<Annotation> annotations = InheritableAnnotations.getDeclaredAnnotations(B.class, "fun");
        assertEquals(3, annotations.size());
    }

    @Test
    public void getDeclaredAnnotationsSpecific() {
        List<Foo> foos = InheritableAnnotations.getDeclaredAnnotations(Foo.class, B.class, "fun");
        assertEquals(2, foos.size());
        List<Bar> bars = InheritableAnnotations.getDeclaredAnnotations(Bar.class, B.class, "fun");
        assertEquals(1, bars.size());
    }

}
