package net.bodz.bas.potato.spi.util;

import org.junit.Assert;
import org.junit.Test;

import user.bean.AddressBean;

import net.bodz.bas.potato.traits.IProperty;
import net.bodz.bas.potato.traits.IPropertyMap;
import net.bodz.bas.trait.Traits;

public class DefaultPropertyMapTest
        extends Assert {

    @Test
    public void testGetPropertyMap() {
        IPropertyMap propertyMap = Traits.getTrait(AddressBean.class, IPropertyMap.class);
        assertEquals(4, propertyMap.size());
    }

    @Test
    public void testGetProperty()
            throws Exception {
        AddressBean addr = new AddressBean();

        IPropertyMap propertyMap = Traits.getTrait(AddressBean.class, IPropertyMap.class);

        IProperty cityProp = propertyMap.getProperty("city");
        assertEquals("city", cityProp.getName());
        assertSame(AddressBean.class, cityProp.getDeclaringType());

        cityProp.set(addr, "New York");
        assertEquals("New York", addr.getCity());
    }

}
