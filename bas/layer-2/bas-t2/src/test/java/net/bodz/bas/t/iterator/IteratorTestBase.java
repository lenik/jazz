package net.bodz.bas.t.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;

public class IteratorTestBase
        extends Assert {

    Integer a[] = { 10, 20, 30, 40, 50 };
    Integer b[] = { 22, 23, 24, 25 };
    Integer c[] = { 5, 15, 25, 26, 28, 45, 55 };
    Integer d[] = { 60 };
    Integer e[] = {};
    Integer f[] = { 31, 53, 22, 15, 5 };

    List<Integer> al = Arrays.asList(a);
    List<Integer> bl = Arrays.asList(b);
    List<Integer> cl = Arrays.asList(c);
    List<Integer> dl = Arrays.asList(d);
    List<Integer> el = Arrays.asList(e);
    List<Integer> fl = Arrays.asList(f);

    String scan(Iterator<?> it) {
        StringBuilder buf = null;
        while (it.hasNext()) {
            Object v = it.next();
            if (buf == null) {
                buf = new StringBuilder();
                buf.append("[");
            } else
                buf.append(", ");
            buf.append(v);
        }
        if (buf == null)
            return "[]";
        buf.append("]");
        return buf.toString();
    }
}
