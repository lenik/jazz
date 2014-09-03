package net.bodz.bas.dbi.expr;

public interface IExpression {

    // Object reduce();

    IBoolExpr eq(IExpression o);

    IBoolExpr neq(IExpression o);

}

class Equals
        extends BinaryBoolExpr {

    public Equals(IExpression a, IExpression b) {
        super(a, b);
    }

}

class NotEquals
        extends BinaryBoolExpr {

    public NotEquals(IExpression a, IExpression b) {
        super(a, b);
    }

}
