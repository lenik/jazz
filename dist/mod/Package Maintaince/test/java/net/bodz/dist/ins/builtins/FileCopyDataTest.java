package net.bodz.dist.ins.builtins;

import net.bodz.bas.xml.XMLs;
import net.bodz.dist.ins.builtins.FileCopy.Data;

import org.junit.Test;

public class FileCopyDataTest {

    @Test
    public void testXMLEncode() throws Exception {
        Data data = new Data();
        data.setList(new String[] { "hello", "world" });
        data.setSumSize(1234);
        String xml = XMLs.encode(data);
        Object rest = XMLs.decode(xml);
        System.out.println(xml);
        System.out.println(rest);
    }

}
