package net.bodz.bas.c.type;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TypePoSetTest
        extends Assert {

    @Test
    public void testAdd() {
        TypePoSet set = new TypePoSet();
        set.add(Number.class);
        set.add(Object.class);
        set.add(String.class);

        assertEquals(Number.class, set.meet(Float.class));
        assertNull(set.meet(List.class));
        assertEquals(Object.class, set.meet(ArrayList.class));

        set.add(Double.class);
        set.add(Integer.class);
        assertEquals(Number.class, set.meet(Float.class));
        assertNull(set.meet(List.class));
        assertEquals(Object.class, set.meet(ArrayList.class));
    }

}
