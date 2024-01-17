package net.bodz.lily.model.base;

import org.joda.time.DateTime;

import net.bodz.lily.criteria.DateFieldCriteriaBuilder;
import net.bodz.lily.criteria.NumberExprCriteriaBuilder;

public class CoMomentIntervalCriteriaBuilder<self_t extends CoMomentIntervalCriteriaBuilder<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final DateField<DateTime> beginTime = date("t0", DateTime.class);
    public final DateField<DateTime> endTime = date("t1", DateTime.class);

    public final NumberExprCriteriaBuilder<DateFieldCriteriaBuilder<self_t, DateTime>> year = beginTime.year;
    public final DateField<DateTime> date = beginTime;

    public void noDate() {
        beginTime.isNull();
        endTime.isNull();
    }

    public void started() {
        beginTime.isNotNull();
    }

    public void ended() {
        endTime.isNotNull();
    }

}
