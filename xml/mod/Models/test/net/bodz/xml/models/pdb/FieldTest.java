package net.bodz.xml.models.pdb;

import net.bodz.xml.util.XMLTest;

import org.junit.Test;

public class FieldTest {

    static Field addressField = new Field();
    static {
        addressField.setName("address");
        addressField.setLabel("地址信息");
        addressField.setDoc("地址是一个空间上的方位");
        addressField.setOpts("NDiF(n:1 -> globe.location)");
    }

    @Test
    public void test() throws Exception {
        XMLTest.testMarshal(addressField);
    }

}
