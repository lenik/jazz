package net.bodz.lily.model.base;

import java.time.ZonedDateTime;

import net.bodz.lily.criteria.DateFieldCriterionBuilder;
import net.bodz.lily.criteria.NumberExprCriteriaBuilder;

public class CoMomentIntervalCriteriaBuilder<self_t extends CoMomentIntervalCriteriaBuilder<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final DateField<ZonedDateTime> beginTime = date("t0", ZonedDateTime.class);
    public final DateField<ZonedDateTime> endTime = date("t1", ZonedDateTime.class);

    public final NumberExprCriteriaBuilder<DateFieldCriterionBuilder<self_t, ZonedDateTime>> year = beginTime.year;
    public final DateField<ZonedDateTime> date = beginTime;

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
