package net.bodz.bas.t.specmap;

import java.util.Set;

public interface IRangeMap<key_t extends Comparable<key_t>, val_t> {

    IRange<key_t> findRangeFor(key_t key);

    default boolean containsRangeFor(key_t key) {
        IRange<key_t> range = findRangeFor(key);
        return range != null;
    }

    boolean hasRange();

    Set<IRange<key_t>> rangeKeySet();

    boolean containsRange(key_t start, key_t end);

    val_t getRange(IRange<key_t> range);

    val_t getRange(key_t start, key_t end);

    val_t putRange(key_t start, key_t end, val_t val);

    default boolean addRange(key_t start, key_t end, val_t val) {
        if (containsRange(start, end))
            return false;
        putRange(start, end, val);
        return true;
    }

    default val_t getOrAddRange(key_t start, key_t end, val_t initial) {
        if (addRange(start, end, initial))
            return initial;
        else
            return getRange(start, end);
    }

    val_t removeRange(IRange<key_t> range);

    val_t removeRange(key_t start, key_t end);

    void removeAllRanges();

}
