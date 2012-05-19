package net.bodz.bas.flow;

import net.bodz.bas.c.type.TypeName;

public class UnitMetaEntryLoader
        extends MetaClassEntryLoader<IUnitMeta> {

    @Override
    protected IUnitMeta createWithoutAnnotation(Class<?> clazz) {
        String name = TypeName.friendly_name(clazz);
        IUnitMeta meta = new AbstractUnitMeta(name);
        return meta;
    }

    static final UnitMetaEntryLoader INSTANCE = new UnitMetaEntryLoader();

}
