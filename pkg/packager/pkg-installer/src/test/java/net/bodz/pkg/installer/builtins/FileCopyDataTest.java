package net.bodz.pkg.installer.builtins;

import org.junit.Test;

import net.bodz.bas.c.xml.XMLs;

public class FileCopyDataTest {

    @Test
    public void testXMLEncode()
            throws Exception {
        FileCopyData data = new FileCopyData();
        data.setList(new String[] { "hello", "world" });
        data.setSumSize(1234);
        String xml = XMLs.encode(data);
        Object rest = XMLs.decode(xml);
        System.out.println(xml);
        System.out.println(rest);
    }

}
