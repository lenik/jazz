package net.bodz.bas.flow;

import java.util.Collection;

import net.bodz.bas.collection.preorder.TypeHierSet;

/**
 * Multi-Type Port
 */
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
        this.baseTypes = new TypeHierSet();
        this.baseTypes.addAll(baseTypes);
    }

    public MTPortMeta(String name, TypeHierSet baseTypes) {
        super(name);
        this.baseTypes = baseTypes;
    }

    @Override
    public boolean isSupportedType(Class<?> type) {
        return baseTypes.floor(type) != null;
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
