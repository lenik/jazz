package net.bodz.lily.repr;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;

import net.bodz.lily.model.base.CoEntity;

@IndexedType
public abstract class CoEntityManager
        implements IPathDispatchable {

    protected Class<? extends CoEntity> entityType;

    public CoEntityManager(Class<? extends CoEntity> entityType) {
        if (entityType == null)
            throw new NullPointerException("entityType");
        this.entityType = entityType;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        String token = tokens.peek();

        switch (token) {
        case "new":
            CoEntity obj;
            try {
                obj = entityType.newInstance();
            } catch (Exception e) {
                throw new PathDispatchException(e.getMessage(), e);
            }
            return PathArrival.shift(previous, obj, tokens);
        }

        return null;
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
