package net.bodz.bas.repr.request;

import java.util.Comparator;

public class RequestPreprocessorComparator
        implements Comparator<IRequestPreprocessor> {

    @Override
    public int compare(IRequestPreprocessor o1, IRequestPreprocessor o2) {
        int cmp = o1.getPriority() - o2.getPriority();
        if (cmp == 0) {
            String n1 = o1.getClass().getName();
            String n2 = o2.getClass().getName();
            cmp = n1.compareTo(n2);
        }
        return cmp;
    }

    static final RequestPreprocessorComparator instance = new RequestPreprocessorComparator();

    public static RequestPreprocessorComparator getInstance() {
        return instance;
    }

}
