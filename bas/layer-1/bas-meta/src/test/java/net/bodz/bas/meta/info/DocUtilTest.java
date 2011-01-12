package net.bodz.bas.meta.info;

import junit.framework.TestCase;
import net.bodz.bas.meta.info.Doc;
import net.bodz.bas.meta.info.DocUtil;
import net.bodz.bas.util.exception.ParseException;

import org.junit.Test;

public class DocUtilTest
        extends TestCase {

    @Test
    public void testDoc_NoDoc()
            throws ParseException {
        String doc = DocUtil.getDoc(Object.class);
        assertNull(doc);
    }

    @Doc("Example String")
    static class DocInLine {
    }

    @Test
    public void testDoc_InLine()
            throws ParseException {
        String doc = DocUtil.getDoc(DocInLine.class);
        assertEquals("Example String", doc);
    }

    @Doc("$DocInResource.txt")
    static class DocInResource {
    }

    @Test
    public void testDoc_ReadClassResource()
            throws ParseException {
        String classDoc = DocUtil.getClassDoc(DocInResource.class);
        assertEquals("sample text file", classDoc);
    }

    @Doc("A")
    static class A {
    }

    static class B
            extends A {
    }

    @Doc("C")
    static class C
            extends B {
    }

    @Test
    public void testClassDoc_1()
            throws ParseException {
        String doc = DocUtil.getClassDoc(A.class);
        assertEquals("A", doc);
    }

    @Test
    public void testClassDoc_AllOccurences()
            throws ParseException {
        String merged = DocUtil.getClassDoc(C.class, true, false, ", ");
        assertEquals("A, C", merged);
    }

    @Test
    public void testClassDoc_AllOccurences_Reversed()
            throws ParseException {
        String merged = DocUtil.getClassDoc(C.class, true, true, ", ");
        assertEquals("C, A", merged);
    }

}
