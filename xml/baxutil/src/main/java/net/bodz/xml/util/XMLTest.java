package net.bodz.xml.util;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.xml.XMLs;

public class XMLTest {

    public static void testMarshal(Object jaxbObject) {
        testMarshal(jaxbObject, true);
    }

    public static void testMarshal(Object jaxbObject, boolean verbose) {
        Class<?> type = jaxbObject.getClass();
        if (verbose)
            System.out.println("Test object:  " + jaxbObject);
        String xml = XMLs.marshal(jaxbObject);
        if (verbose)
            System.out.println(xml);

        Object unmarshaled = XMLs.unmarshal(xml, type);
        if (verbose)
            System.out.println("Reconstructed object: " + unmarshaled);
        String remarshaled = XMLs.marshal(unmarshaled);
        assertEquals(xml, remarshaled);

        if (verbose)
            System.out.println();
    }

}
