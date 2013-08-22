package net.bodz.bas.rtx;

public class Option
        extends AbstractOption {

    private static final long serialVersionUID = 1L;

    public Option(String id, Object value) {
        super(id, value);
    }

    public <T> Option(Class<T> clazz, T value) {
        super(clazz, value);
    }

    public Option(Object typedValue) {
        super(typedValue);
    }

}
