package net.bodz.bas.text.diff;

import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;

public class DiffFormatMdaFeatures
        extends AbstractCommonMdaFeatures<IDiffFormat> {

    public DiffFormatMdaFeatures() {
        super(IDiffFormat.class);
        addStaticFieldsToStore(IDiffFormat.class);
    }

    @Override
    protected Object _query(int mdaFeaturesIndex) {
        return null;
    }

}
