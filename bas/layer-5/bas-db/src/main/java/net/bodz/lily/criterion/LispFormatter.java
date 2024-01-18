package net.bodz.lily.criterion;

public class LispFormatter
        extends AbstractFormatter {

    StringBuilder sb = new StringBuilder();

    public LispFormatter(StringBuilder sb) {
        this.sb = sb;
    }

    @Override
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
    void field(String op, FieldCriterion field, Object... values) {
        sb.append("(");
        if (! field.yes)
            sb.append("~");
        sb.append(op);
        sb.append(" ");
        sb.append(field.fieldName);
        for (Object val : values) {
            sb.append(" ");
            sb.append(val);
        }
        sb.append(")");
    }

}
