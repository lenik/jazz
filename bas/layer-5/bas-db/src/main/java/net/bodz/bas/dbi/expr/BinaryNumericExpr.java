package net.bodz.bas.dbi.expr;

public abstract class BinaryNumericExpr
        extends AbstractNumericExpr {

    INumericExpr a;
    INumericExpr b;

    public BinaryNumericExpr(INumericExpr a, INumericExpr b) {
        this.a = a;
        this.b = b;
    }

    public INumericExpr getA() {
        return a;
    }

    public void setA(INumericExpr a) {
        this.a = a;
    }

    public INumericExpr getB() {
        return b;
    }

    public void setB(INumericExpr b) {
        this.b = b;
    }

}
