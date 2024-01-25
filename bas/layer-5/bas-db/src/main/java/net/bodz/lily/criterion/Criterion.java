package net.bodz.lily.criterion;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.IStack;

public abstract class Criterion
        implements
            ICriterion {

    @Override
    public final void jsonIn(JsonVariant in, JsonFormOptions opts)
            throws ParseException {
        IStack<String> fieldNameStack = new ArrayStack<>();
        jsonIn(in, (stack) -> Object.class, fieldNameStack);
    }

    @Override
    public final void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        jsonIn(JsonVariant.of(o), opts);
    }

    @Override
    public boolean wantObjectContext() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        LispFormatter formatter = new LispFormatter(buf);
        accept(formatter);
        return buf.toString();
    }

    public String getSqlCondition() {
        StringBuilder buf = new StringBuilder();
        SQLFormatter formatter = new SQLFormatter(buf);
        accept(formatter);
        String sql = buf.toString();
        if (sql.equals("()"))
            return "";
        return sql;
    }

}
