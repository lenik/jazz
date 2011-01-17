package net.bodz.bas.lang.negotiation;


public class Optional
        extends NegotiationParameter {

    private static final long serialVersionUID = 1L;

    public Optional(Class<?> type, Object value) {
        super(type, value, false);
    }

    public Optional(Object typedValue) {
        super(typedValue, false);
    }

    public Optional(String id, Object value) {
        super(id, value, false);
    }

}
