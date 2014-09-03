package net.bodz.bas.dbi.expr;

public interface IOrderedExpr
        extends IExpression {

    IBoolExpr lessThan(IOrderedExpr o);

    IBoolExpr lessEq(IOrderedExpr o);

    IBoolExpr greaterThan(IOrderedExpr o);

    IBoolExpr greaterEq(IOrderedExpr o);

    IBoolExpr between(IOrderedExpr a, IOrderedExpr b);

}

class LessThan
        extends BinaryBoolExpr {

    public LessThan(IOrderedExpr a, IOrderedExpr b) {
        super(a, b);
    }

}

class LessEq
        extends BinaryBoolExpr {

    public LessEq(IOrderedExpr a, IOrderedExpr b) {
        super(a, b);
    }

}

class GreaterThan
        extends BinaryBoolExpr {

    public GreaterThan(IOrderedExpr a, IOrderedExpr b) {
        super(a, b);
    }

}

class GreaterEq
        extends BinaryBoolExpr {

    public GreaterEq(IOrderedExpr a, IOrderedExpr b) {
        super(a, b);
    }

}

class Between
        extends AbstractBoolExpr {

    IOrderedExpr val;
    IOrderedExpr lower;
    IOrderedExpr upper;

    public Between(IOrderedExpr val, IOrderedExpr lower, IOrderedExpr upper) {
        if (val == null)
            throw new NullPointerException("val");
        if (lower == null)
            throw new NullPointerException("lower");
        if (upper == null)
            throw new NullPointerException("upper");
        this.val = val;
        this.lower = lower;
        this.upper = upper;
    }

    public IOrderedExpr getVal() {
        return val;
    }

    public void setVal(IOrderedExpr val) {
        this.val = val;
    }

    public IOrderedExpr getLower() {
        return lower;
    }

    public void setLower(IOrderedExpr lower) {
        this.lower = lower;
    }

    public IOrderedExpr getUpper() {
        return upper;
    }

    public void setUpper(IOrderedExpr upper) {
        this.upper = upper;
    }

}
