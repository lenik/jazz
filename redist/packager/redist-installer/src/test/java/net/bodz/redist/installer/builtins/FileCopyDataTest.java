package net.bodz.redist.installer.builtins;

import org.junit.Test;

import net.bodz.bas.xml.XMLs;
import net.bodz.redist.installer.builtins.FileCopy.Data;

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
