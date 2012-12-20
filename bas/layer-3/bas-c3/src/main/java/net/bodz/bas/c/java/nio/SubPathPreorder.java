package net.bodz.bas.c.java.nio;

import net.bodz.bas.t.preorder.AbstractPreorder;

public class SubPathPreorder
        extends AbstractPreorder<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    @Override
    public int precompare(String o1, String o2) {
        if (o1.equals(o2))
            return EQUALS;

        if (o1.startsWith(o2)) {
            if (o2.endsWith("/"))
                return GREATER_THAN;
            if (o1.charAt(o2.length()) == '/')
                return GREATER_THAN;
        }

        if (o2.startsWith(o1)) {
            if (o1.endsWith("/"))
                return LESS_THAN;
            if (o2.charAt(o1.length()) == '/')
                return LESS_THAN;
        }

        return UNKNOWN;
    }

    @Override
    public String getPreceding(String o) {
        if (o.isEmpty())
            return null;

        int slash = o.lastIndexOf('/');
        if (slash == -1)
            return "";

        return o.substring(0, slash);
    }

    public static final SubPathPreorder INSTANCE = new SubPathPreorder();

}
