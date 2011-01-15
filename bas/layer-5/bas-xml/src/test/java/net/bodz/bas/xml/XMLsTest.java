package net.bodz.bas.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.util.exception.DecodeException;
import net.bodz.bas.util.exception.EncodeException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLsTest
        extends Assert {

    @Test
    public void testParse1()
            throws Exception {
        String xml = "<root>" + //
                "<a>A</a>" + //
                "<b>BB</b>" + //
                "<a>AA</a>" + //
                "</root>";
        final Map<String, Integer> tagstat = new TreeMap<String, Integer>();

        XMLs.parse(new StringReader(xml), new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String name, Attributes attributes)
                    throws SAXException {
                Integer stat = tagstat.get(name);
                if (stat == null)
                    stat = 1;
                else
                    stat++;
                tagstat.put(name, stat);
            }
        });

        assertEquals((Integer) 2, tagstat.get("a"));
        assertEquals((Integer) 1, tagstat.get("b"));
    }

    void testXmlCodec(String name, Object val)
            throws EncodeException, DecodeException {
        System.out.println("XML encode/decode test of " + name);
        String xml = XMLs.encode(val);
        System.out.println("  Encoded to" + name + ": \n" + xml);
        Object decoded = XMLs.decode(xml);
        System.out.println("  Decoded to " + decoded);
        System.out.println();
    }

    @Test
    public void testEncodeNull()
            throws IOException {
        String nullXml = XMLs.encode(null);
        System.out.println("null-xml = " + nullXml);
        Object null2 = XMLs.decode(nullXml);
        assertNull(null2);
    }

    @Test
    public void testEncodeString()
            throws IOException {
        testXmlCodec("string", "Hello, <&world>!\n");
    }

    @Test
    public void testEncodeArray()
            throws IOException {
        testXmlCodec("array", new Object[] {
                //
                10, //
                new Integer[] { 20, 21, 22 }, //
                30 });
    }

}
