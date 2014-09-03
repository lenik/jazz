package net.bodz.bas.dbi.expr;

public abstract class AbstractExpression
        implements IExpression {

    @Override
    public IBoolExpr eq(IExpression o) {
        return new Equals(this, o);
    }

    @Override
    public IBoolExpr neq(IExpression o) {
        return new NotEquals(this, o);
    }

}
