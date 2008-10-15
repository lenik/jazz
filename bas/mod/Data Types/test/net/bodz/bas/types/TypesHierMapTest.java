package net.bodz.bas.types;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.types.testtypes.C;
import net.bodz.bas.types.testtypes.CI;
import net.bodz.bas.types.testtypes.CJ;
import net.bodz.bas.types.testtypes.CJz;
import net.bodz.bas.types.testtypes.CJzKI;
import net.bodz.bas.types.testtypes.Cat;
import net.bodz.bas.types.testtypes.D;
import net.bodz.bas.types.testtypes.DIJ;
import net.bodz.bas.types.testtypes.DIJy;
import net.bodz.bas.types.testtypes.Dog;
import net.bodz.bas.types.testtypes.Dx;
import net.bodz.bas.types.testtypes.DxKI;
import net.bodz.bas.types.testtypes.DxKIx;
import net.bodz.bas.types.testtypes.I;
import net.bodz.bas.types.util.Collections2;
import net.bodz.bas.types.util.Types;

import org.junit.Test;

public class TypesHierMapTest {

    static Class<?>[] r(Class<?>... classes) {
        return classes;
    }

    Map<Class<?>[], Object> orig = new HashMap<Class<?>[], Object>();
    {
        orig.put(r(Cat.class, Dog.class), "Cat, Dog");
        orig.put(r(C.class, Dog.class), "C, Dog");
        orig.put(r(Cat.class, D.class), "Cat, D");
        orig.put(r(C.class, D.class), "C, D");
        orig.put(r(CI.class, Dx.class), "CI, Dx");
        orig.put(r(CI.class, DxKI.class), "CI, DxKI");
        orig.put(r(CJz.class, DxKI.class), "CJz, DxKI");
        orig.put(r(CJz.class, DIJy.class), "CJz, DIJy");
        orig.put(r(CJzKI.class, DIJ.class), "CJzKI, DIJ");
    }

    void dumpOrig() {
        System.out.println("Orig entry order: ");
        for (Entry<Class<?>[], Object> e : orig.entrySet()) {
            Class<?>[] key = e.getKey();
            System.out.println(Types.joinNames(", ", true, key));
        }
        System.out.println();
    }

    @Test
    public void test1() {
        TypesHierMap<Object> map = new TypesHierMap<Object>();
        map.putAll(orig);

        // dump entry order
        for (Entry<Class<?>[], Object> e : map.entrySet()) {
            Class<?>[] key = e.getKey();
            System.out.println(Types.joinNames(", ", true, key));
        }

        List<Object> children;

        assertEquals("CI, DxKI", map.floor(r(CI.class, DxKIx.class)));

        children = Collections2.toList(map.ceilings(r(CI.class, D.class)));
        assertEquals(2, children.size());
        assertEquals("CI, Dx", children.get(0));
        assertEquals("CI, DxKI", children.get(1));

        children = Collections2.toList(map.ceilings(r(CJ.class, I.class)));
        assertEquals(3, children.size());
        // DIJy extends DIJ, but CJz order take precedence.
        assertEquals("CJz, DIJy", children.get(0)); // DIJy
        assertEquals("CJz, DxKI", children.get(1));
        assertEquals("CJzKI, DIJ", children.get(2)); // DIJ

        assertEquals(null, map.floor(r(CJzKI.class, Dx.class)));
    }

}
