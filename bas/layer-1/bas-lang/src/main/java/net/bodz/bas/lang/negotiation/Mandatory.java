package net.bodz.bas.lang.negotiation;


public class Mandatory
        extends NegotiationParameter {

    private static final long serialVersionUID = 1L;

    public Mandatory(Class<?> type, Object value) {
        super(type, value, true);
    }

    public Mandatory(Object typedValue) {
        super(typedValue, true);
    }

    public Mandatory(String id, Object value) {
        super(id, value, true);
    }

}
