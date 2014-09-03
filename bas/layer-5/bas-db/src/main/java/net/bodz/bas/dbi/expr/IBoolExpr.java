package net.bodz.bas.dbi.expr;

public interface IBoolExpr
        extends IExpression {

    IBoolExpr not();

    IBoolExpr and(IBoolExpr o);

    IBoolExpr or(IBoolExpr o);

}

class Not
        extends AbstractExpression
        implements IBoolExpr {

    IBoolExpr expr;

    public Not(IBoolExpr expr) {
        this.expr = expr;
    }

    @Override
    public IBoolExpr not() {
        return expr;
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

class And
        extends BinaryBoolExpr {

    public And(IExpression a, IExpression b) {
        super(a, b);
    }

}

class Or
        extends BinaryBoolExpr {

    public Or(IExpression a, IExpression b) {
        super(a, b);
    }

}