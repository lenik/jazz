package net.bodz.bas.t.preorder;

public class StartswithPreorder
        extends AbstractPreorder<String> {

    @Override
    public int compare2(String o1, String o2) {
        return o1.compareTo(o2);
    }

    /**
     * @throws NullPointerException
     *             if <code>string</code> is <code>null</code>.
     */
    @Override
    public String getPreceding(String string) {
        if (string.isEmpty())
            return null;
        return string.substring(0, string.length() - 1);
    }

    @Override
    public int precompare(String o1, String o2) {
        if (o1 == o2)
            return EQUALS;
        int ncmp = o1.length() - o2.length();
        if (ncmp < 0)
            return o2.startsWith(o1) ? LESS_THAN : UNKNOWN;
        if (ncmp > 0)
            return o1.startsWith(o2) ? GREATER_THAN : UNKNOWN;
        return o1.equals(o2) ? EQUALS : UNKNOWN;
    }

    public static final StartswithPreorder INSTANCE = new StartswithPreorder();

}
