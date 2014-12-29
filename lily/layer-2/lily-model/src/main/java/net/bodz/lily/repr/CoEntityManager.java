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
import net.bodz.bas.rtx.AbstractQueryable;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;

import net.bodz.lily.model.base.CoEntity;

@IndexedType
public abstract class CoEntityManager
        extends AbstractQueryable
        implements IPathDispatchable {

    private Class<? extends CoEntity> entityType;
    private IQueryable context;

    public CoEntityManager(Class<? extends CoEntity> entityType, IQueryable context) {
        if (entityType == null)
            throw new NullPointerException("entityType");
        if (context == null)
            throw new NullPointerException("context");
        this.entityType = entityType;
        this.context = context;
    }

    public Class<? extends CoEntity> getEntityType() {
        return entityType;
    }

    /** ⇱ Implementation Of {@link IQueryable}. */
    /* _____________________________ */static section.iface __QUERYABLE__;

    @Override
    public Object query(Object specification)
            throws QueryException {
        return context.query(specification);
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationClass) {
        return context.query(specificationClass);
    }

    @Override
    public Object query(String specificationId) {
        return context.query(specificationId);
    }

    /** ⇱ Implementation Of {@link IPathDispatchable}. */
    /* _____________________________ */static section.iface __PATH_DISP__;

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
