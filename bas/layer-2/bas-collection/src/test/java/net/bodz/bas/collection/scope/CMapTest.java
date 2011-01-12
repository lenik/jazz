package net.bodz.bas.collection.scope;

import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

public class CMapTest
        extends TestCase {

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
