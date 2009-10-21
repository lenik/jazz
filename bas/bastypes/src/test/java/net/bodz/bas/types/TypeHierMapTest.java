package net.bodz.bas.types;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TypeHierMapTest {

    Map<Class<?>, Object> orig = new HashMap<Class<?>, Object>();
    {
        orig.put(Number.class, "Number"); //$NON-NLS-1$
    }

    @Test(expected = ClassCastException.class)
    @Deprecated
    public void test1() {
        TypeHierMap<Object> map = new TypeHierMap<Object>(orig);
        assertEquals("getparent", "Number", map.floor(Integer.class)); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void test2() {
        TypeHierMap<Object> map = new TypeHierMap<Object>();
        map.putAll(orig);
        assertEquals("getparent", "Number", map.floor(Integer.class)); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
