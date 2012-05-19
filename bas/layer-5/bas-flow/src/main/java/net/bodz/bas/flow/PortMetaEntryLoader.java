package net.bodz.bas.flow;

import net.bodz.bas.c.type.TypeName;

public class PortMetaEntryLoader
        extends MetaClassEntryLoader<IPortMeta> {

    @Override
    protected IPortMeta createWithoutAnnotation(Class<?> clazz) {
        String name = TypeName.friendly_name(clazz);
        IPortMeta meta = new STPortMeta(name, Object.class);
        return meta;
    }

    static final PortMetaEntryLoader INSTANCE = new PortMetaEntryLoader();

}
