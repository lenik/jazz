package net.bodz.bas.meta.info;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class DisplayNameUtilTest
        extends Assert {

    @Test
    public void testRegularStem() {
        String stem = DisplayNameUtil.getClassStemName(ArrayList.class);
        assertEquals("Array", stem);
    }

    static abstract class AbstractFoo {
    }

    static abstract class SpecialFoo
            extends AbstractFoo {
    }

    static abstract class HelloWorldFoo
            extends SpecialFoo {
    }

    @Test
    public void testSkippedStem() {
        String stem = DisplayNameUtil.getClassStemName(HelloWorldFoo.class);
        assertEquals("HelloWorld", stem);
    }

}
