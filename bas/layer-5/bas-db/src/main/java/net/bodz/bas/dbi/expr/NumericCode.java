package net.bodz.bas.dbi.expr;

public class NumericCode
        extends AbstractNumericExpr {

    String code;

    public NumericCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
