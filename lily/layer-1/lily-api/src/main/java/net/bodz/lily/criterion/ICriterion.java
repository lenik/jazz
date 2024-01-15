package net.bodz.lily.criterion;

import net.bodz.bas.c.org.json.JsonBuffer;

public interface ICriterion {

    void accept(ICriterionVisitor visitor);

    default String toLisp() {
        StringBuilder buf = new StringBuilder();
        LispFormatter formatter = new LispFormatter(buf);
        accept(formatter);
        return buf.toString();
    }

    default String toJson() {
        JsonBuffer buf = new JsonBuffer();
        JsonDumper jd = new JsonDumper(buf);
        accept(jd);
        return buf.toString();
    }

}
