package net.bodz.bas.flow.unit.metadata;

import java.util.Arrays;
import java.util.Collection;


public class StrictPortMeta
        extends AbstractPortMetadata {

    private final Class<?> baseType;

    public StrictPortMeta(String name, Class<?> baseType) {
        super(name);
        this.baseType = baseType;
    }

    @Override
    public boolean isSupportedType(Class<?> type) {
        return baseType.isAssignableFrom(type);
    }

    @Override
    public Collection<Class<?>> getSupportedTypes() {
        Class<?>[] v = { baseType };
        return Arrays.asList(v);
    }

    @Override
    public int getTypeOrder(Class<?> type) {
        return 0;
    }

}
