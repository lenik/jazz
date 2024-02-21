package net.bodz.lily.criteria;

import java.util.Arrays;
import java.util.Collection;

import net.bodz.lily.criterion.ICriterion;

public interface IDiscreteFieldCriterionSender<fin_target, This, T>
        extends
            ICriterionBuilder,
            IDiscreteFieldCriterionBuilder<T> {

    fin_target send(ICriterion criterion);

    default fin_target isNull() {
        return send(makeIsNull());
    }

    default fin_target isNotNull() {
        return send(makeIsNotNull());
    }

    default fin_target eq(T value) {
        return send(makeEq(value));
    }

    default fin_target notEq(T value) {
        return send(makeEq(value));
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

}
