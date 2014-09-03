package net.bodz.bas.dbi.expr;

public abstract class AbstractNumericExpr
        extends AbstractOrderedExpr
        implements INumericExpr {

    @Override
    public INumericExpr plus(INumericExpr o) {
        return new Plus(this, o);
    }

    @Override
    public INumericExpr minus(INumericExpr o) {
        return new Minus(this, o);
    }

    @Override
    public INumericExpr minusBy(INumericExpr o) {
        return new Minus(o, this);
    }

    @Override
    public INumericExpr multiply(INumericExpr o) {
        return new Multiply(this, o);
    }

    @Override
    public INumericExpr divide(INumericExpr o) {
        return new Divide(this, o);
    }

    @Override
    public INumericExpr divideBy(INumericExpr o) {
        return new Divide(o, this);
    }

    @Override
    public INumericExpr mod(INumericExpr o) {
        return new Mod(this, o);
    }

    @Override
    public INumericExpr inverse() {
        return new Inverse(this);
    }

}
