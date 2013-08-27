package net.bodz.bas.text.diff;

import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;

public class DiffComparatorTypeFeatures
        extends AbstractCommonTypeFeatures<IDiffComparator> {

    public DiffComparatorTypeFeatures() {
        super(IDiffComparator.class);
        addStaticFieldsToStore(DiffComparators.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        return null;
    }

}
