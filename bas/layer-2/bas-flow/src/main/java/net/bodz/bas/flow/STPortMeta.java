package net.bodz.bas.flow;

import java.util.Arrays;
import java.util.Collection;

public class STPortMeta
        extends AbstractPortMeta {

    private final Class<?> baseType;

    public STPortMeta(String name, Class<?> baseType) {
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
