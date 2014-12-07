package net.bodz.bas.flow.unit.metadata;

import net.bodz.bas.c.type.TypeName;

public class UnitMetadataEntryLoader
        extends MetadataEntryLoader<IUnitMetadata> {

    @Override
    protected IUnitMetadata createWithoutAnnotation(Class<?> clazz) {
        String name = TypeName.friendly_name(clazz);
        IUnitMetadata meta = new AbstractUnitMetadata(name);
        return meta;
    }

    public static final UnitMetadataEntryLoader INSTANCE = new UnitMetadataEntryLoader();

}
