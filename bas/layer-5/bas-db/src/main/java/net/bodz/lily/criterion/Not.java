package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.list.IStack;

public class Not
        extends Composite {

    static final String K_NOT = "<not>";

    public Not() {
    }

    @Override
    public ICriterion reduce() {
        switch (size()) {
        case 0:
            return null;
        case 1:
            ICriterion first = get(0);
            if (first.isFieldCriterion())
                return ((FieldCriterion) first).negate();
        }
        return this;
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.not(this);
    }

    @Override
    public void jsonIn(JsonVariant in, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException {
        JsonObject o = in.getObject();
        String firstKey = o.keys().next();
        if (! K_NOT.equals(firstKey))
            throw new ParseException("the only property key should be " + K_NOT);
        Object any = o.get(K_NOT);
        super.jsonIn(JsonVariant.of(any), typeInferrer, fieldNameStack);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.object();
        out.key(K_NOT);
        super.jsonOut(out, opts);
        out.endObject();
    }

}
