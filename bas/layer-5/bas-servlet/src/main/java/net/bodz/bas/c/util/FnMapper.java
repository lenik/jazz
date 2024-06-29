package net.bodz.bas.c.util;

import java.util.function.Function;

public class FnMapper<S, T>
        extends MapGlobal<S, T> {

    Function<S, T> creator;

    public FnMapper(Class<T> targetType, Function<S, T> creator) {
        super(targetType);
        if (creator == null)
            throw new NullPointerException("creator");
        this.creator = creator;
    }

    @Override
    protected T create(S source) {
        return creator.apply(source);
    }

}
