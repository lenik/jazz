package net.bodz.bas.dbi.expr;

public class BoolCode
        extends AbstractBoolExpr {

    String code;

    public BoolCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
