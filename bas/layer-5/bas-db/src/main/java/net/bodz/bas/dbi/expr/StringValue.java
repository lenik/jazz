package net.bodz.bas.dbi.expr;

public class StringValue
        extends AbstractStringExpr
        implements IStringExpr {

    String s;

    public StringValue(String s) {
        if (s == null)
            throw new NullPointerException("s");
        this.s = s;
    }

}
