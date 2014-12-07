package net.bodz.bas.flow.unit.metadata;

import net.bodz.bas.c.type.TypeName;

public class PortMetadataEntryLoader
        extends MetadataEntryLoader<IPortMetadata> {

    @Override
    protected IPortMetadata createWithoutAnnotation(Class<?> clazz) {
        String name = TypeName.friendly_name(clazz);
        IPortMetadata meta = new StrictPortMetadata(name, Object.class);
        return meta;
    }

    public static final PortMetadataEntryLoader INSTANCE = new PortMetadataEntryLoader();

}
