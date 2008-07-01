package net.bodz.bas.types.util;

import net.bodz.bas.lang.Closure;

import org.junit.Test;

public class PermsTest {

    int count = 0;

    @Test
    public void testIterator() {
        char[] array = "654321".toCharArray();
        Perms.iterate(array, new Closure<Object>() {
            @Override
            public void execute(Object o) {
                char[] cc = (char[]) o;
                count++;
                // System.out.println(cc);
                String s = new String(cc);
                int pos = s.indexOf('1');
                s = s.replaceAll("[^1]", "0");
                int bin = Integer.parseInt(s, 2);
                System.out.printf("%8d - %8d - %8d\n", count, pos, bin);
            }
        });
        System.out.println("Count: " + count);
        System.out.println(array);
    }

}
