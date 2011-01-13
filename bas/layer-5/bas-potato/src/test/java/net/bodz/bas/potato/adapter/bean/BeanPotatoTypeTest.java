package net.bodz.bas.potato.adapter.bean;

import java.beans.IntrospectionException;

import net.bodz.bas.potato.PotatoException;
import net.bodz.bas.potato.adapter.bean.user.AddressBean;

import org.junit.Assert;
import org.junit.Test;

public class BeanPotatoTypeTest
        extends Assert {

    AddressBean addressBean = new AddressBean();

    BeanPotatoType<AddressBean> addressType;

    public BeanPotatoTypeTest()
            throws IntrospectionException {
        addressType = new BeanPotatoType<AddressBean>(AddressBean.class);
    }

    @Test
    public void testGetProperties() {
        // 3 country props + 1 ("getClass").
        assertEquals(4, addressType.getProperties().size());
    }

    @Test
    public void testGetProperty()
            throws PotatoException {
        BeanPotatoProperty cityProp = addressType.getProperty("city");
        assertEquals("city", cityProp.getName());
        assertSame(addressType, cityProp.getDeclaringType());

        cityProp.set(addressBean, "New York");
        assertEquals("New York", cityProp.get(addressBean));
    }

}
