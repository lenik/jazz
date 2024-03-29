package net.bodz.bas.t.preorder;

public class DomainNamePreorder
        extends AbstractPreorder<String> {

    @Override
    public int compare2(String o1, String o2) {
        return o1.compareTo(o2);
    }

    @Override
    public String getPreceding(String o) {
        if (o == null)
            return null;
        int dot = o.indexOf('.');
        if (dot == -1)
            return null;
        String parentDomain = o.substring(dot + 1);
        return parentDomain;
    }

    @Override
    public int precompare(String o1, String o2) {
        int len1 = o1.length();
        int len2 = o2.length();
        if (len1 < len2) {
            if (o2.charAt(len2 - len1 - 1) == '.')
                return o2.endsWith(o1) ? LESS_THAN : UNKNOWN;
        } else if (len1 > len2) {
            if (o1.charAt(len1 - len2 - 1) == '.')
                return o1.endsWith(o2) ? GREATER_THAN : UNKNOWN;
        } else if (o1.equals(o2))
            return EQUALS;
        return UNKNOWN;
    }

    public static final DomainNamePreorder INSTANCE = new DomainNamePreorder();

}
