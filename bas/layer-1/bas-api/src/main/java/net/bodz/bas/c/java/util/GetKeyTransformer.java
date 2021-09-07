package net.bodz.bas.c.java.util;

import java.util.Map.Entry;
import java.util.function.Function;

public class GetKeyTransformer<K>
implements Function<Entry<K, ?>, K> {

    @Override
    public K apply(Entry<K, ?> input)
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
