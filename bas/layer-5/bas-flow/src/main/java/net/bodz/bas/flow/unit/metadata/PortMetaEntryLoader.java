package net.bodz.bas.flow.unit.metadata;

import net.bodz.bas.c.type.TypeName;

public class PortMetaEntryLoader
        extends MetaClassEntryLoader<IPortMeta> {

    @Override
    protected IPortMeta createWithoutAnnotation(Class<?> clazz) {
        String name = TypeName.friendly_name(clazz);
        IPortMeta meta = new StrictPortMeta(name, Object.class);
        return meta;
    }

    public static final PortMetaEntryLoader INSTANCE = new PortMetaEntryLoader();

}
