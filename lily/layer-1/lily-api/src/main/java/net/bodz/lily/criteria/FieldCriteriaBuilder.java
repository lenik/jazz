package net.bodz.lily.criteria;

import net.bodz.lily.criterion.*;

public class FieldCriteriaBuilder<fin_target, self_t, T> {

    String fieldName;

    fin_target finishTarget;
    IReceiver<? super FieldCriterion> receiver;

    public FieldCriteriaBuilder(String fieldName, fin_target finishTarget, IReceiver<? super FieldCriterion> receiver) {
        this.fieldName = fieldName;
        this.finishTarget = finishTarget;
    }

    @SuppressWarnings("unchecked")
    protected NumberExprCriteriaBuilder<self_t> number(String expr) {
        return new NumberExprCriteriaBuilder<self_t>(expr, (self_t) this, receiver);
    }

    public final fin_target isNull() {
        receiver.accept(new FieldNull(fieldName, true));
        return finishTarget;
    }

    public final fin_target isNotNull() {
        receiver.accept(new FieldNull(fieldName, false));
        return finishTarget;
    }

    public final fin_target eq(T value) {
        receiver.accept(new FieldEquals<T>(fieldName, false, value));
        return finishTarget;
    }

    public final fin_target notEq(T value) {
        receiver.accept(new FieldEquals<T>(fieldName, true, value));
        return finishTarget;
    }

    public fin_target lessThan(T value) {
        receiver.accept(new FieldCompare<T>(fieldName, false, CompareMode.LESS_THAN, value));
        return finishTarget;
    }

    public fin_target lessOrEquals(T value) {
        receiver.accept(new FieldCompare<T>(fieldName, false, CompareMode.LESS_OR_EQUALS, value));
        return finishTarget;
    }

    public fin_target greaterThan(T value) {
        receiver.accept(new FieldCompare<T>(fieldName, false, CompareMode.GREATER_THAN, value));
        return finishTarget;
    }

    public fin_target greaterOrEquals(T value) {
        receiver.accept(new FieldCompare<T>(fieldName, false, CompareMode.GREATER_OR_EQUALS, value));
        return finishTarget;
    }

    public fin_target between(T min, T max) {
        receiver.accept(new FieldBetween<T>(fieldName, false, min, max));
        return finishTarget;
    }

    public fin_target notBetween(T min, T max) {
        receiver.accept(new FieldBetween<T>(fieldName, true, min, max));
        return finishTarget;
    }

    @SafeVarargs
    public final fin_target in(T... values) {
        receiver.accept(new FieldIn<T>(fieldName, false, values));
        return finishTarget;
    }

    @SafeVarargs
    public final fin_target notIn(T... values) {
        receiver.accept(new FieldIn<T>(fieldName, true, values));
        return finishTarget;
    }

}
