package net.bodz.bas.potato.adapter.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class BeanPotatoTypeTest
        extends BeanTestBase {

    @Test
    public void testOverall()
            throws Exception {
        AddressBean myAddr = new AddressBean();

        BeanPotatoType<AddressBean> addressType = new BeanPotatoType<AddressBean>(AddressBean.class);

        // 3 country props + 1 ("getClass").
        assertEquals(4, addressType.getProperties().size());

        BeanPotatoProperty cityProp = addressType.getProperty("city");
        assertEquals("city", cityProp.getName());

        cityProp.set(myAddr, "New York");
        assertEquals("New York", cityProp.get(myAddr));

        assertSame(addressType, cityProp.getDeclaringType());

        // addressType.getMethods();
    }

}
