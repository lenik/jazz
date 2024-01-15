package net.bodz.lily.criteria;

import org.joda.time.DateTime;

import net.bodz.lily.criterion.FieldCriterion;

public class DateFieldCriteriaBuilder<fin_target>
        extends FieldCriteriaBuilder<fin_target, DateFieldCriteriaBuilder<fin_target>, DateTime> {

    NumberExprCriteriaBuilder<DateFieldCriteriaBuilder<fin_target>> year = number("year");
    NumberExprCriteriaBuilder<DateFieldCriteriaBuilder<fin_target>> month = number("month");
    NumberExprCriteriaBuilder<DateFieldCriteriaBuilder<fin_target>> day = number("day");

    public DateFieldCriteriaBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super FieldCriterion> receiver) {
        super(fieldName, finishTarget, receiver);
    }

}
