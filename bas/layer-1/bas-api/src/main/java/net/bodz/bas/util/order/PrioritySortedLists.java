package net.bodz.bas.util.order;

import java.util.Collections;
import java.util.List;

public class PrioritySortedLists {

    public static <T extends IPriority> void add(List<T> list, T element) {
        int insert = Collections.binarySearch(list, element, PriorityComparator.INSTANCE);
        if (insert < 0)
            insert = -insert - 1;
        list.add(insert, element);
    }

    public static void remove(List<?> list, Object element) {
        list.remove(element);
    }

}
