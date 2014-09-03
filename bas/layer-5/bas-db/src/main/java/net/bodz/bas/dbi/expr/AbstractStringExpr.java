package net.bodz.bas.dbi.expr;

public abstract class AbstractStringExpr
        extends AbstractOrderedExpr
        implements IStringExpr {

    @Override
    public IStringExpr concat(IStringExpr o) {
        return new Concat(this, o);
    }

    @Override
    public IBoolExpr like(String pattern) {
        return new Like(this, pattern);
    }

    @Override
    public IBoolExpr notLike(String pattern) {
        return like(pattern).not();
    }

}
