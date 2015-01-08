package net.bodz.lily.model.base;

import java.lang.reflect.Constructor;

import net.bodz.bas.fn.IEvaluable;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.rtx.AbstractQueryable;
import net.bodz.bas.rtx.IQueryable;

public class CoObjectController
        extends AbstractQueryable
        implements IPathDispatchable, IEvaluable<CoObjectIndex> {

    private IQueryable context;
    private Class<?> objectType;
    private Constructor<? extends CoObjectIndex> indexCtor;

    public CoObjectController(IQueryable context, Class<?> objectType, Class<? extends CoObjectIndex> indexClass) {
        if (context == null)
            throw new NullPointerException("context");
        if (objectType == null)
            throw new NullPointerException("entityType");
        this.context = context;
        this.objectType = objectType;
        try {
            this.indexCtor = indexClass.getConstructor(IQueryable.class);
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Can't get constructor from " + indexClass, e);
        }
    }

    public Class<?> getObjectType() {
        return objectType;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        String token = tokens.peek();

        switch (token) {
        case "index.html":
            return PathArrival.shift(previous, createIndex(), tokens);
        }

        return null;
    }

    protected CoObjectIndex createIndex() {
        try {
            return indexCtor.newInstance(context);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public CoObjectIndex eval() {
        return createIndex();
    }

}
