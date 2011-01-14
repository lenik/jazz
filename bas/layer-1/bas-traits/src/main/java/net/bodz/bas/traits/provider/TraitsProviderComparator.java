package net.bodz.bas.traits.provider;

import java.util.Comparator;

public class TraitsProviderComparator
        implements Comparator<ITraitsProvider> {

    @Override
    public int compare(ITraitsProvider o1, ITraitsProvider o2) {
        assert o1 != null && o2 != null : "Compare with null";

        int cmp = o1.getPriority() - o2.getPriority();
        if (cmp != 0)
            return cmp;

        String o1ClassName = o1.getClass().getName();
        String o2ClassName = o2.getClass().getName();
        return o1ClassName.compareTo(o2ClassName);
    }

    private static final TraitsProviderComparator instance = new TraitsProviderComparator();

    public static TraitsProviderComparator getInstance() {
        return instance;
    }

}
