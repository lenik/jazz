package net.bodz.bas.annotation.util;

import static org.junit.Assert.assertEquals;

import java.lang.annotation.Annotation;

import net.bodz.bas.sio.BCharOut;

import org.junit.Test;

@XyzHint("TeST")
public class AnnotationsTest {

    static String annPrefix(Class<? extends Annotation> annotationClass) {
        return annotationClass + " @" + annotationClass.getName();
    }

    @Test
    public void testDumpAnnotationMap() {
        BCharOut buffer = new BCharOut();
        AnnotationDump.dumpAnnotationMap(AnnotationsTest.class, buffer);
        // @net.bodz.bas.annotations.Author(value=[TeST])
        assertEquals(annPrefix(XyzHint.class) + "(value=[TeST])\n", // 
                buffer.toString());
    }

}
