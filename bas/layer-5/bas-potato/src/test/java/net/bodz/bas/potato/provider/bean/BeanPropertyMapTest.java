package net.bodz.bas.potato.provider.bean;

import org.junit.Assert;
import org.junit.Test;

import user.bean.AddressBean;

import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.potato.traits.IProperty;
import net.bodz.bas.potato.traits.IPropertyMap;
import net.bodz.bas.traits.Traits;

public class BeanPropertyMapTest
        extends Assert {

    AddressBean addressBean = new AddressBean();

    @Test
    public void testGetPropertyMap() {
        IPropertyMap propertyMap = Traits.getTraits(AddressBean.class, IPropertyMap.class);
        assertEquals(4, propertyMap.size());
    }

    @Test
    public void testGetProperty()
            throws ReflectiveOperationException {
        IPropertyMap propertyMap = Traits.getTraits(AddressBean.class, IPropertyMap.class);

        IProperty cityProp = propertyMap.get("city");
        assertEquals("city", cityProp.getName());
        assertSame(AddressBean.class, cityProp.getDeclaringType());

        cityProp.set(addressBean, "New York");
        assertEquals("New York", cityProp.get(addressBean));
    }

}
