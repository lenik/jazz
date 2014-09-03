package net.bodz.bas.dbi.expr;

public class StringCode
        extends AbstractStringExpr {

    String code;

    public StringCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
