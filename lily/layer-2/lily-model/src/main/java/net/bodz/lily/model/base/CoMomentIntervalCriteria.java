package net.bodz.lily.model.base;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateRange;

import net.bodz.lily.model.sea.QVariantMap;

public class CoMomentIntervalCriteria
        extends CoEntityCriteria {

    DateRange dateRange;

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        dateRange = map.getDateRange("dates", dateRange);
    }

}
