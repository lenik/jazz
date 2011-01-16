package net.bodz.bas.potato.traits;

public abstract class AbstractProperty
        extends AbstractMember
        implements IProperty {

    private boolean bound;
    private boolean constrained;

    public AbstractProperty(Class<?> declaringType, String propertyName) {
        super(declaringType, propertyName);
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
