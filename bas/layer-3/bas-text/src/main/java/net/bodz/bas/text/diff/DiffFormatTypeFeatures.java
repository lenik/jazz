package net.bodz.bas.text.diff;

import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;

public class DiffFormatTypeFeatures
        extends AbstractCommonTypeFeatures<IDiffFormat> {

    public DiffFormatTypeFeatures() {
        super(IDiffFormat.class);
        addStaticFieldsToStore(IDiffFormat.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        return null;
    }

}
