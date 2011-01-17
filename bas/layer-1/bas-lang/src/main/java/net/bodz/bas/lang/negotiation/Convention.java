package net.bodz.bas.lang.negotiation;


/**
 * An negotation parameter alias.
 */
public class Convention
        extends NegotiationParameter {

    private static final long serialVersionUID = 1L;

    public Convention(Class<?> type, Object value, boolean mandatory) {
        super(type, value, mandatory);
    }

    public Convention(Class<?> type, Object value) {
        super(type, value);
    }

    public Convention(Object typedValue, boolean mandatory) {
        super(typedValue, mandatory);
    }

    public Convention(Object typedValue) {
        super(typedValue);
    }

    public Convention(String id, Object value, boolean mandatory) {
        super(id, value, mandatory);
    }

    public Convention(String id, Object value) {
        super(id, value);
    }

}
