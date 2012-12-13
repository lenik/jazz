package net.bodz.bas.c.type;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TypePoMapTest
        extends Assert {

    Map<Class<?>, Object> orig = new HashMap<Class<?>, Object>();
    {
        orig.put(Number.class, "Number");
        orig.put(Integer.class, "Integer");
        orig.put(String.class, "String");
    }

    @Test
    public void testFloor() {
        TypePoMap<Object> map = new TypePoMap<Object>();
        map.putAll(orig);
        assertEquals("Number", map.meet(Float.class));
        assertEquals("Integer", map.meet(Integer.class));
        assertNull(map.meet(Date.class));
    }

}
