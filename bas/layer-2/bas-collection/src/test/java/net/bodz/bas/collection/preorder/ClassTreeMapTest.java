package net.bodz.bas.collection.preorder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ClassTreeMapTest
        extends Assert {

    Map<Class<?>, Object> orig = new HashMap<Class<?>, Object>();
    {
        orig.put(Number.class, "Number");
        orig.put(Integer.class, "Integer");
        orig.put(String.class, "String");
    }

    @Test
    public void testFloor() {
        TypePrMap<Object> map = new TypePrMap<Object>();
        map.putAll(orig);
        assertEquals("Number", map.floor(Float.class));
        assertEquals("Integer", map.floor(Integer.class));
        assertNull(map.floor(Date.class));
    }

}
