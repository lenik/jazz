package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public interface IDualFieldCriterionSender<fin_target, This, T>
        extends
            IDualFieldCriterionBuilder<T> {

    fin_target send(ICriterion criterion);

    default fin_target eq(T value1, T value2) {
        return send(makeEq(value1, value2));
    }

    default fin_target notEq(T value1, T value2) {
        return send(makeNotEq(value1, value2));
    }

    default fin_target intersects(T start, T end, boolean includeEnd) {
        return send(makeIntersects(start, end, includeEnd));
    }

    default fin_target notIntersects(T start, T end, boolean includeEnd) {
        return send(makeNotIntersects(start, end, includeEnd));
    }

    default fin_target intersects(T start, T end) {
        return send(makeIntersects(start, end, false));
    }

    default fin_target notIntersects(T start, T end) {
        return send(makeNotIntersects(start, end, false));
    }

}
