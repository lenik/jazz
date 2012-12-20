package net.bodz.bas.c.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.type.testtype.*;
import net.bodz.bas.t.iterator.Iterables;

public class TypeVectorPoMapTest
        extends Assert {

    static Class<?>[] array(Class<?>... classes) {
        return classes;
    }

    Map<Class<?>[], Object> orig = new HashMap<Class<?>[], Object>();
    {
        orig.put(array(Cat.class, Dog.class), "Cat, Dog");
        orig.put(array(C.class, Dog.class), "C, Dog");
        orig.put(array(Cat.class, D.class), "Cat, D");
        orig.put(array(C.class, D.class), "C, D");
        orig.put(array(CI.class, Dx.class), "CI, Dx");
        orig.put(array(CI.class, DxKI.class), "CI, DxKI");
        orig.put(array(CJz.class, DxKI.class), "CJz, DxKI");
        orig.put(array(CJz.class, DIJy.class), "CJz, DIJy");
        orig.put(array(CJzKI.class, DIJ.class), "CJzKI, DIJ");
    }

    static String joinSimpleName(Class<?>[] classes) {
        StringBuilder buf = null;
        for (Class<?> c : classes) {
            if (buf == null)
                buf = new StringBuilder(classes.length * 30);
            else
                buf.append(", ");
            buf.append(c.getSimpleName());
        }
        return buf == null ? "" : buf.toString();
    }

    void dumpOrig() {
        System.out.println("Orig entry order: ");
        for (Entry<Class<?>[], Object> e : orig.entrySet()) {
            Class<?>[] key = e.getKey();
            System.out.println(joinSimpleName(key));
        }
        System.out.println();
    }

    @Test
    public void test1() {
        TypeVectorPoMap<Object> map = new TypeVectorPoMap<Object>();
        map.putAll(orig);

        // dump entry order
        for (Entry<Class<?>[], Object> e : map.entrySet()) {
            Class<?>[] key = e.getKey();
            System.out.println(joinSimpleName(key));
        }

        List<Object> children;

        assertEquals("CI, DxKI", map.meet(array(CI.class, DxKIx.class)));

        children = Iterables.toList(map.join(array(CI.class, D.class)));
        assertEquals(2, children.size());
        assertEquals("CI, Dx", children.get(0));
        assertEquals("CI, DxKI", children.get(1));

        children = Iterables.toList(map.join(array(CJ.class, I.class)));
        assertEquals(3, children.size());
        // DIJy extends DIJ, but CJz order take precedence.
        assertEquals("CJz, DIJy", children.get(0)); // DIJy
        assertEquals("CJz, DxKI", children.get(1));
        assertEquals("CJzKI, DIJ", children.get(2)); // DIJ

        assertEquals(null, map.meet(array(CJzKI.class, Dx.class)));
    }

}
