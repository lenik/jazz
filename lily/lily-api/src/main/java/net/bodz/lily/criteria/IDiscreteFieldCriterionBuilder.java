package net.bodz.lily.criteria;

import java.util.Collection;

import net.bodz.lily.criterion.ICriterion;

public interface IDiscreteFieldCriterionBuilder<T>
        extends
            ICriterionBuilder {

    ICriterion makeIsNull();

    ICriterion makeIsNotNull();

    ICriterion makeEq(T value);

    ICriterion makeNotEq(T value);

    ICriterion makeIn(Collection<T> values);

    ICriterion makeNotIn(Collection<T> values);

}
