package net.bodz.lily.criteria;

import java.util.Arrays;
import java.util.Collection;

import net.bodz.lily.criterion.ICriterion;

public interface IFieldCriterionSender<fin_target, This, T>
        extends
            ICriterionBuilder,
            IFieldCriterionBuilder<T> {

    fin_target send(ICriterion criterion);

    default fin_target isNull() {
        return send(makeIsNull());
    }

    default fin_target isNotNull() {
        return send(makeIsNotNull());
    }

    default fin_target isTrue() {
        return send(makeIsTrue());
    }

    default fin_target isFalse() {
        return send(makeIsFalse());
    }

    default fin_target eq(T value) {
        return send(makeEq(value));
    }

    default fin_target notEq(T value) {
        return send(makeEq(value));
    }

    default fin_target lessThan(T value) {
        return send(makeLessThan(value));
    }

    default fin_target lessOrEquals(T value) {
        return send(makeLessOrEquals(value));
    }

    default fin_target greaterThan(T value) {
        return send(makeGreaterThan(value));
    }

    default fin_target greaterOrEquals(T value) {
        return send(makeGreaterOrEquals(value));
    }

    default fin_target between(T min, T max) {
        return send(makeBetween(min, max));
    }

    default fin_target notBetween(T min, T max) {
        return send(makeNotBetween(min, max));
    }

    @SuppressWarnings("unchecked")
    default fin_target in(T... values) {
        return in(Arrays.asList(values));
    }

    default fin_target in(Collection<T> values) {
        return send(makeIn(values));
    }

    @SuppressWarnings("unchecked")
    default fin_target notIn(T... values) {
        return notIn(Arrays.asList(values));
    }

    default fin_target notIn(Collection<T> values) {
        return send(makeNotIn(values));
    }

    default fin_target inside(T start, T end, boolean includeEnd) {
        return send(makeInside(start, end, includeEnd));
    }

    default fin_target optInside(T start, T end, boolean includeEnd) {
        return send(makeOptInside(start, end, includeEnd));
    }

    default fin_target outside(T start, T end, boolean includeEnd) {
        return send(makeOutside(start, end, includeEnd));
    }

    default fin_target optOutside(T start, T end, boolean includeEnd) {
        return send(makeOptOutside(start, end, includeEnd));
    }

    default fin_target inside(T start, T end) {
        return inside(start, end, false);
    }

    default fin_target optInside(T start, T end) {
        return optInside(start, end, false);
    }

    default fin_target outside(T start, T end) {
        return outside(start, end, false);
    }

    default fin_target optOutside(T start, T end) {
        return optOutside(start, end, false);
    }

}
