package net.bodz.lily.entity.type;

import java.util.Arrays;

import net.bodz.bas.err.IllegalUsageError;
import net.bodz.lily.entity.manager.ForEntityType;

public abstract class AbstractEntityOpListener<T>
        implements
            IEntityOpListener<T> {

    final Class<T> valueType;

    @SuppressWarnings("unchecked")
    public AbstractEntityOpListener() {
        ForEntityType aForType = getClass().getAnnotation(ForEntityType.class);
        if (aForType == null)
            throw new IllegalUsageError("expect @" + ForEntityType.class.getName() + " on " + getClass());

        Class<?>[] classes = aForType.value();
        if (classes.length != 1)
            throw new IllegalUsageError("expect single class: " + Arrays.asList(classes));

        this.valueType = (Class<T>) classes[0];
    }

    public AbstractEntityOpListener(Class<T> valueType) {
        if (valueType == null)
            throw new NullPointerException("valueType");
        this.valueType = valueType;
    }

    @Override
    public Class<T> getValueType() {
        return valueType;
    }

    @Override
    public int getPriority() {
        return 0;
    }

}
