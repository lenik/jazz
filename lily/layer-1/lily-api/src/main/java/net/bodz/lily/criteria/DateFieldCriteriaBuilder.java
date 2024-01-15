package net.bodz.lily.criteria;

import net.bodz.lily.criterion.FieldCriterion;

public class DateFieldCriteriaBuilder<fin_target, T>
        extends FieldCriteriaBuilder<fin_target, DateFieldCriteriaBuilder<fin_target, T>, T> {

    NumberExprCriteriaBuilder<DateFieldCriteriaBuilder<fin_target, T>> year = number("year");
    NumberExprCriteriaBuilder<DateFieldCriteriaBuilder<fin_target, T>> month = number("month");
    NumberExprCriteriaBuilder<DateFieldCriteriaBuilder<fin_target, T>> day = number("day");

    public DateFieldCriteriaBuilder(String fieldName, Class<T> type, fin_target finishTarget,
            IReceiver<? super FieldCriterion> receiver) {
        super(fieldName, type, finishTarget, receiver);
    }

}
