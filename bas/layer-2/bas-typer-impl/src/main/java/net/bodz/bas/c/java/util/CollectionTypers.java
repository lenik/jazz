package net.bodz.bas.c.java.util;

import java.util.Collection;

import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class CollectionTypers<T>
        extends AbstractCommonTypers<Collection<T>> {

    @SuppressWarnings("unchecked")
    public CollectionTypers(Class<T> valueType) {
        super((Class<Collection<T>>) (Class<?>) Collection.class);
    }

    @Override
    public Object query(String specificationId) {
        return super.query(specificationId);
    }

    @Override
    public Object query(Object specification)
            throws QueryException {
        return super.query(specification);
    }

    @Override
    protected Object _query(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

}
