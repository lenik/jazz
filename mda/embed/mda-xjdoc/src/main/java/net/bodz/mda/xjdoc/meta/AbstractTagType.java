package net.bodz.mda.xjdoc.meta;

import java.util.Map.Entry;

public abstract class AbstractTagType
        implements ITagType {

    @Override
    public String format(Object value) {
        if (value == null)
            return null;
        if (value instanceof Entry<?, ?>) {
            Entry<?, ?> entry = (Entry<?, ?>) value;
            return format(entry.getKey(), entry.getValue());
        } else {
            return format(value);
        }
    }

}
