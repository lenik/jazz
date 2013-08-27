package net.bodz.bas.c.java.util;

import java.util.Collection;

import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.tf.std.ISampleGenerator;
import net.bodz.bas.rtx.QueryException;

public class CollectionTypeFeatures<T>
        extends AbstractCommonTypeFeatures<Collection<T>> {

    @SuppressWarnings("unchecked")
    public CollectionTypeFeatures(Class<T> valueType) {
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
    protected Object _query(int typeFeatureIndex) {
        switch (typeFeatureIndex) {
        case IParser.typeFeatureIndex:
        case ISampleGenerator.typeFeatureIndex:
            return this;
        }
        return null;
    }

}
