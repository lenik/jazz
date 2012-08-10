package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.builtins.FileCopy.Data;
import net.bodz.bas.xml.XMLs;

import org.junit.Test;

public class FileCopyDataTest {

    @Test
    public void testXMLEncode()
            throws Exception {
        Data data = new Data();
        data.setList(new String[] { "hello", "world" });
        data.setSumSize(1234);
        String xml = XMLs.encode(data);
        Object rest = XMLs.decode(xml);
        System.out.println(xml);
        System.out.println(rest);
    }

}
