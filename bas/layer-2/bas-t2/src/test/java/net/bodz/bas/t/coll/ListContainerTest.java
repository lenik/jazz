package net.bodz.bas.t.coll;

import org.junit.Assert;
import org.junit.Test;

public class ListContainerTest
        extends Assert {

    @Test
    public void test() {
        ListContainer<Integer> c = new ListContainer<>(Integer.class);
        c.add(10);
        c.add(20);
        c.add(30);
        c.remove(1);
        System.out.println(c);
        c.set(0, 99);
        System.out.println(c);
        c.addNamed(100, "1");
        System.out.println(c);
        c.set("2", 300);
        System.out.println(c);
        c.removeNamed("1");
        System.out.println(c);
    }

}
