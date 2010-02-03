package net.bodz.bas.potato;

public abstract class AbstarctPotatoProperty
        extends AbstractPotatoElement
        implements IPotatoProperty {

    private boolean bound;
    private boolean constrained;

    public AbstarctPotatoProperty(String propertyName) {
        super(propertyName);
    }

    @Override
    public boolean isBound() {
        return bound;
    }

    protected void setBound(boolean bound) {
        this.bound = bound;
    }

    @Override
    public boolean isConstrained() {
        return constrained;
    }

    protected void setConstrained(boolean constrained) {
        this.constrained = constrained;
    }

}
