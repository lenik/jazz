package net.bodz.bas.collection.preorder;

/**
 * @test {@link PackageNamePreorderTest}
 */
public class PackageNamePreorder
        extends AbstractPreorder<String> {

    @Override
    public String getPreceding(String o) {
        if (o == null)
            return null;
        int lastDot = o.lastIndexOf('.');
        if (lastDot == -1)
            return null;
        String parentPackage = o.substring(0, lastDot);
        return parentPackage;
    }

    @Override
    public int precompare(String o1, String o2) {
        int len1 = o1.length();
        int len2 = o2.length();
        if (len1 < len2) {
            if (o2.charAt(len1) == '.')
                return o2.startsWith(o1) ? LESS_THAN : UNKNOWN;
        } else if (len1 > len2) {
            if (o1.charAt(len2) == '.')
                return o1.startsWith(o2) ? GREATER_THAN : UNKNOWN;
        } else if (o1.equals(o2))
            return EQUALS;
        return UNKNOWN;
    }

    static final PackageNamePreorder instance = new PackageNamePreorder();

    public static PackageNamePreorder getInstance() {
        return instance;
    }

}
