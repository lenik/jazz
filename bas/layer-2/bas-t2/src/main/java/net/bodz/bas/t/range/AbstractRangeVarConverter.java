package net.bodz.bas.t.range;

import net.bodz.bas.t.variant.conv.AbstractVarConverter;

public abstract class AbstractRangeVarConverter<range_t extends Range<range_t, val_t>, val_t>
        extends AbstractVarConverter<range_t> {

    public AbstractRangeVarConverter(Class<range_t> type) {
        super(type);
    }

}
