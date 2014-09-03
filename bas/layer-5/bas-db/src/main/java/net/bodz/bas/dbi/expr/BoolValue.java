package net.bodz.bas.dbi.expr;

public class BoolValue
        extends AbstractBoolExpr {

    private final boolean value;

    private BoolValue(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public static final BoolValue TRUE = new BoolValue(true);
    public static final BoolValue FALSE = new BoolValue(false);

}
