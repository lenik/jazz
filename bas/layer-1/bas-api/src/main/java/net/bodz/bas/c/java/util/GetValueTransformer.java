package net.bodz.bas.c.java.util;

import java.util.Map.Entry;

import net.bodz.bas.fn.AbstractTransformer;

public class GetValueTransformer<V>
        extends AbstractTransformer<Entry<?, V>, V> {

    @Override
    public V transform(Entry<?, V> input)
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
