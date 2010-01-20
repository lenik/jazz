package net.bodz.bas.collection.preorder;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class TypeHierSetTest {

    static String join(String delim, Set<?> set) {
        StringBuffer buf = null;
        for (Object o : set) {
            if (buf == null)
                buf = new StringBuffer(set.size() * 30);
            else
                buf.append(delim);
            buf.append(o);
        }
        return buf.toString();
    }

    @Test
    public void test1() {
        TypeHierSet set = new TypeHierSet();
        set.add(Number.class);
        set.add(Object.class);
        set.add(String.class);
        System.out.println(join(", ", set));
        assertEquals(Number.class, set.floor(Float.class));
        assertEquals(Object.class, set.floor(List.class));

        set.add(Double.class);
        set.add(Integer.class);
        System.out.println(join(", ", set));
        assertEquals(Number.class, set.floor(Float.class));
        assertEquals(Object.class, set.floor(List.class));
    }

}
