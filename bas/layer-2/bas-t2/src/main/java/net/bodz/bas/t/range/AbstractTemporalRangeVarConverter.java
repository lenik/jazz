package net.bodz.bas.t.range;

public abstract class AbstractTemporalRangeVarConverter<range_t extends Range<range_t, val_t>, val_t>
        extends AbstractRangeVarConverter<range_t, val_t> {

    public AbstractTemporalRangeVarConverter(Class<range_t> type) {
        super(type);
    }

}
