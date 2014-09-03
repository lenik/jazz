package net.bodz.bas.dbi.expr;

public abstract class AbstractBoolExpr
        extends AbstractExpression
        implements IBoolExpr {

    @Override
    public IBoolExpr not() {
        return new Not(this);
    }

    @Override
    public IBoolExpr and(IBoolExpr o) {
        return new And(this, o);
    }

    @Override
    public IBoolExpr or(IBoolExpr o) {
        return new Or(this, o);
    }

}
