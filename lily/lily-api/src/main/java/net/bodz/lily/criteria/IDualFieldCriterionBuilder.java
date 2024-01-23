package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public interface IDualFieldCriterionBuilder<T> {

    ICriterion makeEq(T value1, T value2);

    ICriterion makeNotEq(T value1, T value2);

    ICriterion makeIntersects(T start, T end, boolean includeEnd);

    ICriterion makeNotIntersects(T start, T end, boolean includeEnd);

}
