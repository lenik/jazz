package net.bodz.bas.flow.unit.metadata;

import net.bodz.bas.c.type.TypeName;

public class UnitMetaEntryLoader
        extends MetaClassEntryLoader<IUnitMetadata> {

    @Override
    protected IUnitMetadata createWithoutAnnotation(Class<?> clazz) {
        String name = TypeName.friendly_name(clazz);
        IUnitMetadata meta = new AbstractUnitMetadata(name);
        return meta;
    }

    public static final UnitMetaEntryLoader INSTANCE = new UnitMetaEntryLoader();

}
