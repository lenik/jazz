package net.bodz.bas.text.diff;

import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;

public class DiffComparatorMdaFeatures
        extends AbstractCommonMdaFeatures<IDiffComparator> {

    public DiffComparatorMdaFeatures() {
        super(IDiffComparator.class);
        addStaticFieldsToStore(DiffComparators.class);
    }

    @Override
    protected Object _query(int mdaFeaturesIndex) {
        return null;
    }

}
