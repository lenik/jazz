package net.bodz.lily.criterion;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.t.list.IStack;

public interface ICriterion
        extends
            IJsonForm {

    default boolean isFieldCriterion() {
        return false;
    }

    void accept(ICriterionVisitor visitor);

    void jsonIn(JsonVariant in, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException;

    default String toJson() {
        JsonBuffer buf = new JsonBuffer();
        accept(new JsonFormatter(buf));
        return buf.toString();
    }

}
