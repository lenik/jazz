package net.bodz.bas.types;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.collection.preorder.TypeHierMap;

import org.junit.Test;

public class TypeHierMapTest {

    Map<Class<?>, Object> orig = new HashMap<Class<?>, Object>();
    {
        orig.put(Number.class, "Number"); 
    }

    @Test(expected = ClassCastException.class)
    @Deprecated
    public void test1() {
        TypeHierMap<Object> map = new TypeHierMap<Object>(orig);
        assertEquals("getparent", "Number", map.floor(Integer.class));  
    }

    @Test
    public void test2() {
        TypeHierMap<Object> map = new TypeHierMap<Object>();
        map.putAll(orig);
        assertEquals("getparent", "Number", map.floor(Integer.class));  
    }

}
