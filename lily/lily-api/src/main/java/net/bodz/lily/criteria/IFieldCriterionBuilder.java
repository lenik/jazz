package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public interface IFieldCriterionBuilder<T>
        extends
            IDiscreteFieldCriterionBuilder<T> {

    ICriterion makeIsTrue();

    ICriterion makeIsFalse();

    ICriterion makeLessThan(T value);

    ICriterion makeLessOrEquals(T value);

    ICriterion makeGreaterThan(T value);

    ICriterion makeGreaterOrEquals(T value);

    ICriterion makeBetween(T min, T max);

    ICriterion makeNotBetween(T min, T max);

    ICriterion makeInside(T start, T end, boolean includeEnd);

    /**
     * Optionally inside: true if the field value is null.
     */
    ICriterion makeOptInside(T start, T end, boolean includeEnd);

    ICriterion makeOutside(T start, T end, boolean includeEnd);

    /**
     * Optionally inside: true if the field value is null.
     */
    ICriterion makeOptOutside(T start, T end, boolean includeEnd);

}
