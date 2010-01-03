package net.bodz.bas.collection.scope;

import java.util.Map;

import net.bodz.bas.collection.scope.CMap;

import org.junit.Test;

public class CMapTest {

    @Test
    public void test1() {
        CMap<Object, Object> cmap = new CMap<Object, Object>();
        cmap.put("a", "1"); //$NON-NLS-1$ //$NON-NLS-2$
        cmap.enterNew();
        cmap.put("a", "2"); //$NON-NLS-1$ //$NON-NLS-2$
        System.out.println(cmap.get("a")); //$NON-NLS-1$
        for (Map.Entry<Object, Object> e : cmap.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue()); //$NON-NLS-1$
        }

        for (Object k : cmap.keySet()) {
            System.out.println(k);
        }

        cmap.leave();
        System.out.println(cmap.get("a")); //$NON-NLS-1$
        for (Map.Entry<Object, Object> e : cmap.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue()); //$NON-NLS-1$
        }
    }

}
