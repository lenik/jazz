package net.bodz.bas.c.java.util;

import java.util.Map.Entry;
import java.util.function.Function;

public class GetValueTransformer<V>
        implements
            Function<Entry<?, V>, V> {

    @Override
    public V apply(Entry<?, V> input)
            throws RuntimeException {
        if (input == null)
            return null;
        else
            return input.getValue();
    }

    static GetValueTransformer<?> INSTANCE = new GetValueTransformer<Object>();

    @SuppressWarnings("unchecked")
    public static <V> GetValueTransformer<V> getInstance() {
        return (GetValueTransformer<V>) INSTANCE;
    }

}
