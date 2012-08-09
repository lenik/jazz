package net.bodz.bas.flow.unit.metadata;

import java.util.Collection;

import net.bodz.bas.c.type.TypePrSet;

/**
 * Multi-Type Port
 */
public class LoosePortMeta
        extends AbstractPortMetadata {

    private final TypePrSet baseTypes;

    public LoosePortMeta(String name, Class<?>... baseTypes) {
        super(name);
        this.baseTypes = new TypePrSet();
        for (Class<?> baseType : baseTypes)
            this.baseTypes.add(baseType);
    }

    public LoosePortMeta(String name, Collection<Class<?>> baseTypes) {
        super(name);
        this.baseTypes = new TypePrSet();
        this.baseTypes.addAll(baseTypes);
    }

    public LoosePortMeta(String name, TypePrSet baseTypes) {
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
