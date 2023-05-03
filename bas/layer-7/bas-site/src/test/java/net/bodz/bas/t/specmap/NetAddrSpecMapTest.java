package net.bodz.bas.t.specmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class NetAddrSpecMapTest
        extends Assert {

    static int[] parse(String addr) {
        String[] split = addr.split("\\.");
        int[] iv = new int[split.length];
        for (int i = 0; i < iv.length; i++)
            iv[i] = Integer.parseInt(split[i]);
        return iv;
    }

    static String format(Set<int[]> intsSet) {
        List<String> list = new ArrayList<>();
        for (int[] ints : intsSet) {
            StringJoiner sj = new StringJoiner(".");
            for (int n : ints)
                sj.add("" + n);
            list.add(sj.toString());
        }
        Collections.sort(list);
        return list.toString();
    }

    @Test
    public void testTopKeySet_discrete()
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.putTop("1.2.3.4", "a");
        map.putTop("2.3.4.5", "b");
        map.putTop("1.2.3.5", "a2");

        Set<int[]> keys = map.topKeySet();
        assertEquals("[1.2.3.4, 1.2.3.5, 2.3.4.5]", format(keys));
    }

    @Test
    public void testTopKeySet_discrete_emptyByDel()
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.putTop("1.2.3.4", "a");
        map.putTop("1.2.3.5", "a2");
        map.putTop("2.3.4.5", "b");
        map.removeTop("1.2.3.5");

        Set<int[]> keys = map.topKeySet();
        assertEquals("[1.2.3.4, 2.3.4.5]", format(keys));
    }

    @Test
    public void testTopKeySet_tree()
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.putTop(parse("1"), "a");
        map.putTop(parse("1.2"), "b");
        map.putTop(parse("1.2.3"), "c");
        map.putTop(parse("1.2.3.4"), "d");

        Set<int[]> keys = map.topKeySet();
        assertEquals("[1, 1.2, 1.2.3, 1.2.3.4]", format(keys));
    }

    @Test
    public void testTopKeySet_treeWithHole()
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.putTop(parse("1"), "a");
        map.putTop(parse("1.2"), "b");
        map.putTop(parse("1.2.3.4"), "d");

        Set<int[]> keys = map.topKeySet();
        assertEquals("[1, 1.2, 1.2.3.4]", format(keys));
    }

    @Test
    public void testTopKeySet_treeWithHole_ByDel()
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.putTop(parse("1"), "a");
        map.putTop(parse("1.2"), "b");
        map.putTop(parse("1.2.3"), "c");
        map.putTop(parse("1.2.3.4"), "d");
        map.removeTop(parse("1.2.3"));

        Set<int[]> keys = map.topKeySet();
        assertEquals("[1, 1.2, 1.2.3.4]", format(keys));
    }

    @Test
    public void testTopKeySet_treeWithHole_ByDel_danglingLong()
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.putTop(parse("1"), "a");
        map.putTop(parse("1.2"), "b");
        map.putTop(parse("1.2.3"), "c");
        map.putTop(parse("1.2.3.4"), "d");
        map.removeTop(parse("1.2.3"));
        map.removeTop(parse("1.2.3.4"));

        System.out.println(map);
        Set<int[]> keys = map.topKeySet();
        assertEquals("[1, 1.2]", format(keys));
    }

}
