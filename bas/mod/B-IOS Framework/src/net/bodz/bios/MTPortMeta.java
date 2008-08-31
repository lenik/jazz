package net.bodz.bios;

import java.util.Collection;

import net.bodz.bas.types.TypeHierSet;

public class MTPortMeta extends _PortMeta {

    private final TypeHierSet baseTypes;

    public MTPortMeta(String name, Class<?>... baseTypes) {
        super(name);
        this.baseTypes = new TypeHierSet();
        for (Class<?> baseType : baseTypes)
            this.baseTypes.add(baseType);
    }

    public MTPortMeta(String name, Collection<Class<?>> baseTypes) {
        super(name);
        this.baseTypes = new TypeHierSet(baseTypes);
    }

    public MTPortMeta(String name, TypeHierSet baseTypes) {
        super(name);
        this.baseTypes = baseTypes;
    }

    @Override
    public boolean isSupportedType(Class<?> type) {
        return baseTypes.hasParent(type);
    }

    @Override
    public Collection<Class<?>> getSupportedTypes() {
        return baseTypes;
    }

    @Override
    public int getTypeOrder(Class<?> type) {
        return 0;
    }

}
