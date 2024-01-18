package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.list.IStack;

public class Disjunction
        extends Composite {

    static final String K_OR = "<or>";

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.disjunction(this);
    }

    @Override
    public void jsonIn(JsonVariant in, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException {
        JsonObject o = in.getObject();
        String firstKey = o.keys().next();
        if (! K_OR.equals(firstKey))
            throw new ParseException("the only property key should be " + K_OR);
        Object any = o.get(K_OR);
        super.jsonIn(JsonVariant.of(any), typeInferrer, fieldNameStack);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.object();
        out.key(K_OR);
        super.jsonOut(out, opts);
        out.endObject();
    }

}
