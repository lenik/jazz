package net.bodz.bas.dbi.expr;

public abstract class BinaryBoolExpr
        extends AbstractBoolExpr {

    IExpression a;
    IExpression b;

    public BinaryBoolExpr(IExpression a, IExpression b) {
        if (a == null)
            throw new NullPointerException("a");
        if (b == null)
            throw new NullPointerException("b");
        this.a = a;
        this.b = b;
    }

    public IExpression getA() {
        return a;
    }

    public void setA(IExpression a) {
        this.a = a;
    }

    public IExpression getB() {
        return b;
    }

    public void setB(IExpression b) {
        this.b = b;
    }

}