package net.bodz.lily.gen;

import java.util.ArrayList;
import java.util.Collection;

public class PackageOrderList
        extends ArrayList<String> {

    private static final long serialVersionUID = 1L;

    public PackageOrderList() {
        super();
    }

    public PackageOrderList(Collection<? extends String> c) {
        super(c);
    }

    public int find(String qName) {
        for (int i = 0; i < size(); i++) {
            String pre = get(i);
            int len = pre.length();
            if (qName.startsWith(pre)) {
                if (qName.length() > len && qName.charAt(len) != '.')
                    continue;
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

}
