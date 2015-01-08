package net.bodz.lily.model.base;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.rtx.IQueryable;

@IndexedType
public abstract class CoObjectIndex
        implements IPathDispatchable {

    private Class<?> objectType;
    IQueryable context;

    public CoObjectIndex(IQueryable context) {
        if (context == null)
            throw new NullPointerException("context");
        this.context = context;
        this.objectType = getClass().getAnnotation(ObjectType.class).value();
    }

    public Class<?> getObjectType() {
        return objectType;
    }

    @Override
    public String toString() {
        IType type = PotatoTypes.getInstance().forClass(getClass());
        if (type == null)
            throw new NullPointerException("type");
        iString label = type.getLabel();
        if (label == null)
            throw new NullPointerException("label");
        return label.toString();
    }

}
