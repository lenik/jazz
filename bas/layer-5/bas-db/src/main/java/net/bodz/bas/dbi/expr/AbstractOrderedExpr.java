package net.bodz.bas.dbi.expr;

public abstract class AbstractOrderedExpr
        extends AbstractExpression
        implements IOrderedExpr {

    @Override
    public IBoolExpr lessThan(IOrderedExpr o) {
        return new LessThan(this, o);
    }

    @Override
    public IBoolExpr lessEq(IOrderedExpr o) {
        return new LessEq(this, o);
    }

    @Override
    public IBoolExpr greaterThan(IOrderedExpr o) {
        return new GreaterThan(this, o);
    }

    @Override
    public IBoolExpr greaterEq(IOrderedExpr o) {
        return new GreaterEq(this, o);
    }

    @Override
    public IBoolExpr between(IOrderedExpr a, IOrderedExpr b) {
        return new Between(this, a, b);
    }

}
