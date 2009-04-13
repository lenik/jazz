package net.bodz.bas.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.StringReader;

import net.bodz.bas.lang.err.DecodeException;
import net.bodz.bas.lang.err.EncodeException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLsTest {

    @Test
    public void testParse1() throws Exception {
        String xml = "<root>" + // //$NON-NLS-1$
                "<a>A</a>" + // //$NON-NLS-1$
                "<b>BB</b>" + // //$NON-NLS-1$
                "<a>AA</a>" + // //$NON-NLS-1$
                "</root>"; //$NON-NLS-1$
        final TextMap<Integer> tagstat = new TreeTextMap<Integer>();

        XMLs.parse(new StringReader(xml), new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String name,
                    Attributes attributes) throws SAXException {
                Integer stat = tagstat.get(name);
                if (stat == null)
                    stat = 1;
                else
                    stat++;
                tagstat.put(name, stat);
            }
        });

        assertEquals((Integer) 2, tagstat.get("a")); //$NON-NLS-1$
        assertEquals((Integer) 1, tagstat.get("b")); //$NON-NLS-1$
    }

    void testXmlCodec(String name, Object val) throws EncodeException,
            DecodeException {
        System.out.println("XML encode/decode test of " + name);
        String xml = XMLs.encode(val);
        System.out.println("  Encoded to" + name + ": \n" + xml);
        Object decoded = XMLs.decode(xml);
        System.out.println("  Decoded to " + decoded);
        System.out.println();
    }

    @Test
    public void testEncodeNull() throws IOException {
        String nullXml = XMLs.encode(null);
        System.out.println("null-xml = " + nullXml);
        Object null2 = XMLs.decode(nullXml);
        assertNull(null2);
    }

    @Test
    public void testEncodeString() throws IOException {
        testXmlCodec("string", "Hello, <&world>!\n");
    }

    @Test
    public void testEncodeArray() throws IOException {
        testXmlCodec("array", new Object[] {
        //
                10, //
                new Integer[] { 20, 21, 22 }, //
                30 });
    }

}
