package net.bodz.bas.t.coll;

import java.util.LinkedHashSet;

import org.junit.Assert;
import org.junit.Test;

public class MapContainerTest
        extends Assert {

    LinkedHashSet<String> ls;

    @Test
    public void test() {
        StringMapContainer<Integer> c = new StringMapContainer<>(Integer.class);
        c.add(10);
        c.add(20);
        c.add(30);
        System.out.println(c);
        c.remove(1);
        System.out.println(c);
        Integer old = c.set(0, 99);
        System.out.println("old=" + old);
        System.out.println(c);

        c.add(1, 1000);

        c.addNamed(100, "1");
        System.out.println(c);
        c.set("2", 300);
        System.out.println(c);
        c.removeNamed("1");
        System.out.println(c);
    }

}
