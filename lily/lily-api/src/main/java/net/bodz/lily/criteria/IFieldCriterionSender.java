package net.bodz.lily.criteria;

public interface IFieldCriterionSender<fin_target, This, T>
        extends
            IDiscreteFieldCriterionSender<fin_target, This, T>,
            IFieldCriterionBuilder<T> {

    default fin_target isTrue() {
        return send(makeIsTrue());
    }

    default fin_target isFalse() {
        return send(makeIsFalse());
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
