package net.bodz.bas.dbi.expr;

public interface IStringExpr
        extends IOrderedExpr {

    IStringExpr concat(IStringExpr o);

    IBoolExpr like(String pattern);

    IBoolExpr notLike(String pattern);

}

class Concat
        extends AbstractStringExpr {

    IStringExpr a;
    IStringExpr b;

    public Concat(IStringExpr a, IStringExpr b) {
        this.a = a;
        this.b = b;
    }

}

class Like
        extends AbstractBoolExpr {

    IStringExpr expr;
    String pattern;

    public Like(IStringExpr expr, String pattern) {
        this.expr = expr;
        this.pattern = pattern;
    }

    public IStringExpr getExpr() {
        return expr;
    }

    public void setExpr(IStringExpr expr) {
        this.expr = expr;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}