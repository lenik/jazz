package net.bodz.lily.criteria;

import java.util.Collection;

import net.bodz.lily.criterion.CompareMode;
import net.bodz.lily.criterion.FieldCompare;
import net.bodz.lily.criterion.FieldIn;
import net.bodz.lily.criterion.ICriterion;

public class DiscreteFieldCriterionBuilder<fin_target, This, T>
        extends AbstractDiscreteFieldCriterionBuilder<fin_target, This, T> {

    String fieldName;
    Class<T> type;

    public DiscreteFieldCriterionBuilder(String fieldName, Class<T> type, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(finishTarget, receiver);
        this.fieldName = fieldName;
        this.type = type;
    }

    public Class<T> getValueType() {
        return type;
    }

    @Override
    public ICriterion makeIsNull() {
        return makeEq(null);
    }

    @Override
    public ICriterion makeIsNotNull() {
        return makeNotEq(null);
    }

    @Override
    public ICriterion makeEq(T value) {
        return new FieldCompare<T>(fieldName, true, CompareMode.EQUALS, value);
    }

    @Override
    public ICriterion makeNotEq(T value) {
        return new FieldCompare<T>(fieldName, true, CompareMode.NOT_EQUALS, value);
    }

    @Override
    public ICriterion makeIn(Collection<T> values) {
        return new FieldIn<T>(fieldName, true, values);
    }

    @Override
    public ICriterion makeNotIn(Collection<T> values) {
        return new FieldIn<T>(fieldName, false, values);
    }

}
