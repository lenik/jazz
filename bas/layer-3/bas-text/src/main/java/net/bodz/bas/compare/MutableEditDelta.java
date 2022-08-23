package net.bodz.bas.compare;

public abstract class MutableEditDelta<T>
        implements
            IEditDelta<T> {

    IEditDeltaType type;

    public MutableEditDelta(IEditDeltaType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public IEditDeltaType getType() {
        return type;
    }

}
