package net.bodz.lily.model.base;

import net.bodz.bas.c.java.util.DateInterval;
import net.bodz.bas.err.ParseException;

import net.bodz.lily.model.sea.QVariantMap;

public class CoMomentIntervalCriteria
        extends CoEntityCriteria {

    DateInterval dateInterval;

    @Override
    public DateInterval getDateInterval() {
        return dateInterval;
    }

    @Override
    public void setDateInterval(DateInterval dateInterval) {
        this.dateInterval = dateInterval;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        dateInterval = map.getDateInterval("dates", dateInterval);
    }

}
