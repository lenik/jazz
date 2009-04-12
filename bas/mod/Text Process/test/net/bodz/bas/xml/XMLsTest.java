package net.bodz.bas.xml;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLsTest {

    @Test
    public void testParseXML1() throws Exception {
        String xml = "<root>" + // //$NON-NLS-1$
                "<a>A</a>" + // //$NON-NLS-1$
                "<b>BB</b>" + // //$NON-NLS-1$
                "<a>AA</a>" + // //$NON-NLS-1$
                "</root>"; //$NON-NLS-1$
        final TextMap<Integer> tagstat = new TreeTextMap<Integer>();

        XMLs.parseXML(new StringReader(xml), new DefaultHandler() {
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

}
