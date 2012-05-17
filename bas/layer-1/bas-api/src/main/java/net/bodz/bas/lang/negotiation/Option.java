package net.bodz.bas.lang.negotiation;

public class Option
        extends AbstractParameter {

    private static final long serialVersionUID = 1L;

    public <T> Option(Class<T> type, T value) {
        super(type, value);
    }

    public Option(Object typedValue) {
        super(typedValue);
    }

    public Option(String id, Object value) {
        super(id, value);
    }

    @Override
    public boolean isImportant() {
        return false;
    }

}
