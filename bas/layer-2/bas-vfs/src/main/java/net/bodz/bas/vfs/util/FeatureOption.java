package net.bodz.bas.vfs.util;

import java.io.Serializable;

public class FeatureOption
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Object key;
    private final Object value;

    public FeatureOption(Object key, Object value) {
        if (key == null)
            throw new NullPointerException("key");
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public static FeatureOption[] arrayOf(Object... keyvals) {
        if (keyvals == null)
            throw new NullPointerException("keyvals");
        int pairs = keyvals.length / 2;
        FeatureOption[] array = new FeatureOption[pairs];
        int i = 0;
        for (int pairIndex = 0; pairIndex < pairs; pairIndex++) {
            FeatureOption option = new FeatureOption(keyvals[i], keyvals[i + 1]);
            array[pairIndex] = option;
            i += 2;
        }
        return array;
    }

}
