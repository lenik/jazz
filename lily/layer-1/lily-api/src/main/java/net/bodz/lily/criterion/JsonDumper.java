package net.bodz.lily.criterion;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.IStack;

public class JsonDumper
        implements
            ICriterionVisitor {

    IJsonOut out;
    IStack<Boolean> stack = new ArrayStack<>();

    public JsonDumper(IJsonOut out) {
        this.out = out;
    }

    @Override
    public void not(Not not) {
        out.object();
        out.key("not");
        not.criterion.accept(this);
        out.endObject();
    }

    void composite(String name, Composite composite) {
        if (composite.size() == 1) {
            composite.list.get(0).accept(this);
            return;
        }

        out.object();
        out.key(name);

        boolean fields = true;
        for (ICriterion item : composite)
            if (!(item instanceof FieldCriterion)) {
                fields = false;
                break;
            }

        stack.push(fields);
        if (fields) {
            out.object();
            for (ICriterion item : composite) {
                item.accept(this);
            }
            out.endObject();
        } else {
            out.array();
            for (ICriterion item : composite) {
                item.accept(this);
            }
            out.endArray();
        }
        stack.pop();

        out.endObject();
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
        boolean allFields = stack.top();
        if (!allFields)
            out.object();

        out.key(field.fieldName);
        out.object();

        if (field.not)
            out.key("~" + op);
        else
            out.key(op);

        switch (values.length) {
        case 0:
            out.value(null);
            break;
        case 1:
            out.value(values[0]);
            break;
        default:
            out.array();
            for (Object val : values)
                out.value(val);
            out.endArray();
        }
        out.endObject();

        if (!allFields)
            out.endObject();
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
