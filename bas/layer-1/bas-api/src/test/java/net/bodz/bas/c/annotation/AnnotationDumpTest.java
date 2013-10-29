package net.bodz.bas.c.annotation;

import java.lang.annotation.Annotation;

import org.junit.Assert;
import org.junit.Test;

@XyzHint("TeST")
public class AnnotationDumpTest
        extends Assert {

    /**
     * Return value looks like: XyzHint.class @ XyzHint
     */
    static String formatEntry(Class<? extends Annotation> annotationClass) {
        String annotationClassName = annotationClass.getSimpleName();
        String annotationImplName = annotationClass.getCanonicalName();
        return annotationClassName + ".class => @" + annotationImplName;
    }

    @Test
    public void testDumpAnnotationMap() {
        String text = AnnotationDump.dumpAnnotationMap(AnnotationDumpTest.class);
        // @net.bodz.bas.annotations.Author(value=[TeST])
        assertEquals(formatEntry(XyzHint.class) + "(value=[TeST])\n", //
                text);
    }

}
