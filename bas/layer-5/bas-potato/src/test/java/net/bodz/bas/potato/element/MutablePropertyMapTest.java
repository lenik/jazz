package net.bodz.bas.potato.element;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.typer.Typers;

import user.bean.AddressBean;

public class MutablePropertyMapTest
        extends Assert {

    @Test
    public void testGetPropertyMap() {
        IPropertyMap propertyMap = Typers.getTyper(AddressBean.class, IPropertyMap.class);
        // fields: country, city, address
        // properties: class, country, city, address
        assertEquals(7, propertyMap.getPropertyCount());
    }

    @Test
    public void testGetProperty()
            throws Exception {
        AddressBean addr = new AddressBean();

        IPropertyMap propertyMap = Typers.getTyper(AddressBean.class, IPropertyMap.class);

        IProperty cityProp = propertyMap.getProperty("city");
        assertEquals("city", cityProp.getName());
        assertSame(AddressBean.class, cityProp.getDeclaringClass());

        cityProp.setValue(addr, "New York");
        assertEquals("New York", addr.getCity());
    }

}
