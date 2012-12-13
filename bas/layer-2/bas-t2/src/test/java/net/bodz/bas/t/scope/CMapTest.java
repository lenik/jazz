package net.bodz.bas.t.scope;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t.scope.CMap;

public class CMapTest
        extends Assert {

    @Test
    public void test1() {
        CMap<Object, Object> cmap = new CMap<Object, Object>();
        cmap.put("a", "1");
        cmap.enterNew();
        cmap.put("a", "2");
        System.out.println(cmap.get("a"));
        for (Map.Entry<Object, Object> e : cmap.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }

        for (Object k : cmap.keySet()) {
            System.out.println(k);
        }

        cmap.leave();
        System.out.println(cmap.get("a"));
        for (Map.Entry<Object, Object> e : cmap.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }

}
