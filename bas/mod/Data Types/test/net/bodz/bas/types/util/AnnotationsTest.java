package net.bodz.bas.types.util;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;

import net.bodz.bas.a.Author;
import net.bodz.bas.io.CharOuts.BCharOut;

import org.junit.Test;

@Author("TeST")
public class AnnotationsTest {

    static String annPrefix(Class<? extends Annotation> annotationClass) {
        return annotationClass + " @" + annotationClass.getName();
    }

    @Test
    public void testDumpAnnotationMap() {
        BCharOut buffer = new BCharOut();
        Annotations.dumpAnnotationMap(AnnotationsTest.class, buffer);
        // @net.bodz.bas.annotations.Author(value=[TeST])
        assertEquals(annPrefix(Author.class) + "(value=[TeST])\n", //
                buffer.toString());
    }
}
