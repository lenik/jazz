package net.bodz.bas.repr.path;

import net.bodz.bas.ctx.scope.ScopedAutoloadRef;

public class NoPathScopedRef<T>
        extends ScopedAutoloadRef<T>
        implements INoPathRef {

    public NoPathScopedRef(Class<T> objectType) {
        super(objectType);
    }

    public static <T> NoPathScopedRef<T> of(Class<T> objectType) {
        return new NoPathScopedRef<>(objectType);
    }

    @Override
    public Object getTarget() {
        return getOrLoad();
    }

}
