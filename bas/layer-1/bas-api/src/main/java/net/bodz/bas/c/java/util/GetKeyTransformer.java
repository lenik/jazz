package net.bodz.bas.c.java.util;

import java.util.Map.Entry;

import net.bodz.bas.fn.AbstractTransformer;

public class GetKeyTransformer<K>
        extends AbstractTransformer<Entry<K, ?>, K> {

    @Override
    public K transform(Entry<K, ?> input)
            throws RuntimeException {
        if (input == null)
            return null;
        else
            return input.getKey();
    }

    static GetKeyTransformer<?> INSTANCE = new GetKeyTransformer<Object>();

    @SuppressWarnings("unchecked")
    public static <K> GetKeyTransformer<K> getInstance() {
        return (GetKeyTransformer<K>) INSTANCE;
    }

}
