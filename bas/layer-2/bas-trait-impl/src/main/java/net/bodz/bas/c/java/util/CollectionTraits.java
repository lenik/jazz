package net.bodz.bas.c.java.util;

import java.util.Collection;

import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;

public class CollectionTraits<T>
        extends AbstractCommonTraits<Collection<T>> {

    @SuppressWarnings("unchecked")
    public CollectionTraits(Class<T> valueType) {
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
    protected Object query(int traitIndex) {
        switch (traitIndex) {
        case IParser.traitIndex:
        case ISampleGenerator.traitIndex:
            return this;
        }
        return null;
    }

}
