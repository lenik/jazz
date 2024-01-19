package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public class DateFieldCriterionBuilder<fin_target, T>
        extends FieldCriterionBuilder<fin_target, DateFieldCriterionBuilder<fin_target, T>, T> {

    public final NumberExprCriteriaBuilder<DateFieldCriterionBuilder<fin_target, T>> year = number("year");
    public final NumberExprCriteriaBuilder<DateFieldCriterionBuilder<fin_target, T>> month = number("month");
    public final NumberExprCriteriaBuilder<DateFieldCriterionBuilder<fin_target, T>> day = number("day");

    public DateFieldCriterionBuilder(String fieldName, Class<T> type, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(fieldName, type, finishTarget, receiver);
    }

}
