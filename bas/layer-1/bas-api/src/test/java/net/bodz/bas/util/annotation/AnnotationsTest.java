package net.bodz.bas.util.annotation;

import java.lang.annotation.Annotation;

import org.junit.Assert;
import org.junit.Test;

@XyzHint("TeST")
public class AnnotationsTest
        extends Assert {

    static String annPrefix(Class<? extends Annotation> annotationClass) {
        return annotationClass + " @" + annotationClass.getName();
    }

    @Test
    public void testDumpAnnotationMap() {
        String text = AnnotationDump.dumpAnnotationMap(AnnotationsTest.class);
        // @net.bodz.bas.annotations.Author(value=[TeST])
        assertEquals(annPrefix(XyzHint.class) + "(value=[TeST])\n", //
                text);
    }

}
