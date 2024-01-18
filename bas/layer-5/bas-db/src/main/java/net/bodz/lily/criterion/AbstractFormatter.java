package net.bodz.lily.criterion;

public abstract class AbstractFormatter
        implements
            ICriterionVisitor {

    abstract void composite(String name, Composite composite);

    @Override
    public void not(Not not) {
        composite("not", not);
    }

    @Override
    public void junction(Junction junction) {
        composite("and", junction);
    }

    @Override
    public void disjunction(Disjunction disjunction) {
        composite("or", disjunction);
    }

    abstract void field(String op, FieldCriterion field, Object... values);

    @Override
    public void fieldBetween(FieldBetween<?> fieldBetween) {
        field("between", fieldBetween, fieldBetween.min, fieldBetween.max);
    }

    @Override
    public void fieldCompare(FieldCompare<?> fieldCompare) {
        field(fieldCompare.mode.camelName(), fieldCompare, fieldCompare.value);
    }

    @Override
    public void fieldIn(FieldIn<?> fieldIn) {
        Object[] vals = fieldIn.values.toArray();
        field("in", fieldIn, vals);
    }

    @Override
    public void fieldLike(FieldLike fieldLike) {
        field("like", fieldLike, fieldLike.pattern);
    }

}
