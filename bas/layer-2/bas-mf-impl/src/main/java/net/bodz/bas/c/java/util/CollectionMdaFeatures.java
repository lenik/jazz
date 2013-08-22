package net.bodz.bas.c.java.util;

import java.util.Collection;

import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.mf.std.ISampleGenerator;

public class CollectionMdaFeatures<T>
        extends AbstractCommonMdaFeatures<Collection<T>> {

    @SuppressWarnings("unchecked")
    public CollectionMdaFeatures(Class<T> valueType) {
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
    protected Object query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
        case ISampleGenerator.mdaFeatureIndex:
            return this;
        }
        return null;
    }

}
