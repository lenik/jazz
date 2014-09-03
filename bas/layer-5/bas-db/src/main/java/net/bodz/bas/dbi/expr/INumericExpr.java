package net.bodz.bas.dbi.expr;

public interface INumericExpr
        extends IOrderedExpr {

    INumericExpr plus(INumericExpr o);

    INumericExpr minus(INumericExpr o);

    INumericExpr minusBy(INumericExpr o);

    INumericExpr multiply(INumericExpr o);

    INumericExpr divide(INumericExpr o);

    INumericExpr divideBy(INumericExpr o);

    INumericExpr mod(INumericExpr o);

    INumericExpr inverse();

}

class Plus
        extends BinaryNumericExpr {

    public Plus(INumericExpr a, INumericExpr b) {
        super(a, b);
    }

}

class Minus
        extends BinaryNumericExpr {

    public Minus(INumericExpr a, INumericExpr b) {
        super(a, b);
    }

}

class Multiply
        extends BinaryNumericExpr {

    public Multiply(INumericExpr a, INumericExpr b) {
        super(a, b);
    }

}

class Divide
        extends BinaryNumericExpr {

    public Divide(INumericExpr a, INumericExpr b) {
        super(a, b);
    }

}

class Mod
        extends BinaryNumericExpr {

    public Mod(INumericExpr a, INumericExpr b) {
        super(a, b);
    }

}

class Inverse
        extends AbstractNumericExpr {

    INumericExpr expr;

    public Inverse(INumericExpr expr) {
        this.expr = expr;
    }

}