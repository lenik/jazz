package net.bodz.bas.types.ints;

import java.util.SortedSet;

/**
 * @see SortedSet
 */
public interface IntSortedSet extends IntSet {

    IntComparator comparator();

    IntSortedSet subSet(int fromElement, int toElement);

    IntSortedSet headSet(int toElement);

    IntSortedSet tailSet(int fromElement);

    int first();

    int last();

}
