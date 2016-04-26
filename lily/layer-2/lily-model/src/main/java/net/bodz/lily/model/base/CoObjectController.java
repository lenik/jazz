package net.bodz.lily.model.base;

import java.lang.reflect.Constructor;

import net.bodz.bas.repr.path.INoPathRef;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.rtx.AbstractQueryable;
import net.bodz.bas.rtx.IQueryable;

public class CoObjectController
        extends AbstractQueryable
        implements IPathDispatchable, INoPathRef {

    private IQueryable context;
    private Class<?> objectType;

    private Constructor<? extends CoObjectIndex> indexCtor;
    private CoObjectIndex index;

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
        if (token == null)
            return null;

        switch (token) {
        case "index.html":
            return PathArrival.shift(previous, getTarget(), tokens);
        }

        return null;
    }

    @Override
    public synchronized CoObjectIndex getTarget() {
        if (index == null)
            try {
                index = indexCtor.newInstance(context);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Failed to create index instance: " + e.getMessage(), e);
            }
        return index;
    }

}
