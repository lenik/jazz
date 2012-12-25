package net.bodz.bas.text.diff;

import net.bodz.bas.traits.AbstractCommonTraits;

public class DiffFormatTraits
        extends AbstractCommonTraits<IDiffFormat> {

    public DiffFormatTraits() {
        super(IDiffFormat.class);
        addStaticFieldsToStore(IDiffFormat.class);
    }

    @Override
    protected Object query(int traitsIndex) {
        return null;
    }

}
