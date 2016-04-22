package net.bodz.bas.c.type;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class TypePoMapTest
        extends Assert {

    Map<Class<?>, Object> orig = new HashMap<Class<?>, Object>();
    {
        orig.put(Number.class, "Number");
        orig.put(Integer.class, "Integer");
        orig.put(Long.class, "Long");
        orig.put(Double.class, "Double");
        orig.put(Boolean.class, "Boolean");
        orig.put(String.class, "String");
    }

    @Test
    public void testMeet() {
        TypePoMap<Object> map = new TypePoMap<Object>();
        map.putAll(orig);
        assertEquals("Number", map.meet(Float.class));
        assertEquals("Integer", map.meet(Integer.class));
        assertNull(map.meet(Date.class));
    }

    @Test
    public void testJoin() {
        TypePoMap<Object> map = new TypePoMap<Object>();
        map.putAll(orig);

        Set<Object> set = new HashSet<>();
        for (Object o : map.join(Number.class))
            set.add(o);

        Set<String> expected = new HashSet<>(Arrays.asList(//
                "Number", "Integer", "Long", "Double"));

        assertEquals(expected, set);
    }

}
