package net.bodz.bas.c.java.util;

import java.util.Collection;

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
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

}
