package net.bodz.bas.t.coll;

import org.junit.Assert;
import org.junit.Test;

public class SingleContainerTest
        extends Assert {

    @Test
    public void test() {
        SingleContainer<Integer> c = new SingleContainer<>(Integer.class, OverflowAction.REPLACE);

        c.add(10);
        c.add(11);
        System.out.println(c);
        c.remove(0);
        System.out.println(c);
        c.add(0, 20);
        System.out.println(c);
        c.set(0, 99);
        System.out.println(c);

        c.removeNamed("0");
        System.out.println(c);
        c.addNamed(100, "0");
        System.out.println(c);
        c.set("0", 300);
        System.out.println(c);
    }

}
