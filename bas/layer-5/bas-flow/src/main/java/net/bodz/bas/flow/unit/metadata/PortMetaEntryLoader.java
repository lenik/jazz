package net.bodz.bas.flow.unit.metadata;

import net.bodz.bas.c.type.TypeName;

public class PortMetaEntryLoader
        extends MetaClassEntryLoader<IPortMetadata> {

    @Override
    protected IPortMetadata createWithoutAnnotation(Class<?> clazz) {
        String name = TypeName.friendly_name(clazz);
        IPortMetadata meta = new StrictPortMeta(name, Object.class);
        return meta;
    }

    public static final PortMetaEntryLoader INSTANCE = new PortMetaEntryLoader();

}
