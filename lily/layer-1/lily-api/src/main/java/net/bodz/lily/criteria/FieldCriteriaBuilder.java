package net.bodz.lily.criteria;

import net.bodz.lily.criterion.*;

public class FieldCriteriaBuilder<fin_target, self_t, T> {

    String fieldName;
    Class<T> type;

    fin_target finishTarget;
    IReceiver<? super FieldCriterion> receiver;

    public FieldCriteriaBuilder(String fieldName, Class<T> type, fin_target finishTarget,
            IReceiver<? super FieldCriterion> receiver) {
        this.fieldName = fieldName;
        this.type = type;
        this.finishTarget = finishTarget;
        this.receiver = receiver;
    }

    @SuppressWarnings("unchecked")
    protected NumberExprCriteriaBuilder<self_t> number(String expr) {
        return new NumberExprCriteriaBuilder<self_t>(expr, (self_t) this, receiver);
    }

    public final fin_target isNull() {
        receiver.receive(new FieldNull(fieldName, true));
        return finishTarget;
    }

    public final fin_target isNotNull() {
        receiver.receive(new FieldNull(fieldName, false));
        return finishTarget;
    }

    public final fin_target isTrue() {
        receiver.receive(new FieldTrue(fieldName, false));
        return finishTarget;
    }

    public final fin_target isFalse() {
        receiver.receive(new FieldTrue(fieldName, true));
        return finishTarget;
    }

    public final fin_target eq(T value) {
        receiver.receive(new FieldCompare<T>(fieldName, false, CompareMode.EQUALS, value));
        return finishTarget;
    }

    public final fin_target notEq(T value) {
        receiver.receive(new FieldCompare<T>(fieldName, false, CompareMode.NOT_EQUALS, value));
        return finishTarget;
    }

    public fin_target lessThan(T value) {
        receiver.receive(new FieldCompare<T>(fieldName, false, CompareMode.LESS_THAN, value));
        return finishTarget;
    }

    public fin_target lessOrEquals(T value) {
        receiver.receive(new FieldCompare<T>(fieldName, false, CompareMode.LESS_OR_EQUALS, value));
        return finishTarget;
    }

    public fin_target greaterThan(T value) {
        receiver.receive(new FieldCompare<T>(fieldName, false, CompareMode.GREATER_THAN, value));
        return finishTarget;
    }

    public fin_target greaterOrEquals(T value) {
        receiver.receive(new FieldCompare<T>(fieldName, false, CompareMode.GREATER_OR_EQUALS, value));
        return finishTarget;
    }

    public fin_target between(T min, T max) {
        receiver.receive(new FieldBetween<T>(fieldName, false, min, max));
        return finishTarget;
    }

    public fin_target notBetween(T min, T max) {
        receiver.receive(new FieldBetween<T>(fieldName, true, min, max));
        return finishTarget;
    }

    @SafeVarargs
    public final fin_target in(T... values) {
        receiver.receive(new FieldIn<T>(fieldName, false, values));
        return finishTarget;
    }

    @SafeVarargs
    public final fin_target notIn(T... values) {
        receiver.receive(new FieldIn<T>(fieldName, true, values));
        return finishTarget;
    }

}
