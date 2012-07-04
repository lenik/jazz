package net.bodz.bas.lang.negotiation;

class Parameter
        extends AbstractParameter {

    private static final long serialVersionUID = 1L;

    public <T> Parameter(Class<T> type, T value) {
        super(type, value);
    }

    public Parameter(Object typedValue) {
        super(typedValue);
    }

    public Parameter(String id, Object value) {
        super(id, value);
    }

    @Override
    public boolean isImportant() {
        return true;
    }

}
