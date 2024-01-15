package net.bodz.lily.criterion;

public class LispFormatter
        implements
            ICriterionVisitor {

    StringBuilder sb = new StringBuilder();

    public LispFormatter(StringBuilder sb) {
        this.sb = sb;
    }

    @Override
    public void not(Not not) {
        sb.append("(not ");
        not.criterion.accept(this);
        sb.append(")");
    }

    void composite(String name, Composite composite) {
        sb.append("(");
        sb.append(name);
        for (ICriterion item : composite) {
            sb.append(' ');
            item.accept(this);
        }
        sb.append(")");
    }

    @Override
    public void junction(Junction junction) {
        composite("and", junction);
    }

    @Override
    public void disjunction(Disjunction junction) {
        composite("or", junction);
    }

    void field(String op, FieldCriterion field, Object... values) {
        sb.append("(");
        if (field.not)
            sb.append("!");
        sb.append(op);
        sb.append(" ");
        sb.append(field.fieldName);
        for (Object val : values) {
            sb.append(" ");
            sb.append(val);
        }
        sb.append(")");
    }

    @Override
    public void fieldBetween(FieldBetween<?> fieldBetween) {
        field("between", fieldBetween, fieldBetween.min, fieldBetween.max);
    }

    @Override
    public void fieldCompare(FieldCompare<?> fieldCompare) {
        field(fieldCompare.mode.getOpName(), fieldCompare, fieldCompare.value);
    }

    @Override
    public void fieldEquals(FieldEquals<?> fieldEquals) {
        field("eq", fieldEquals, fieldEquals.value);
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

    @Override
    public void fieldNull(FieldNull fieldNull) {
        field("null", fieldNull);
    }

}
