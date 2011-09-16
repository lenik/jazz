package net.bodz.bas.util.type;

import java.util.List;
import java.util.Set;

import net.bodz.bas.util.type.TypePrSet;

import org.junit.Assert;
import org.junit.Test;

@Deprecated
public class TypePrSetTest
        extends Assert {

    static String join(String delim, Set<?> set) {
        StringBuilder buf = null;
        for (Object o : set) {
            if (buf == null)
                buf = new StringBuilder(set.size() * 30);
            else
                buf.append(delim);
            buf.append(o);
        }
        return buf.toString();
    }

    @Test
    public void test1() {
        TypePrSet set = new TypePrSet();
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
