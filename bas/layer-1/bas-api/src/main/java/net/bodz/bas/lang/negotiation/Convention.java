package net.bodz.bas.lang.negotiation;

/**
 * An negotation parameter alias.
 */
public class Convention
        extends AbstractParameter {

    private static final long serialVersionUID = 1L;

    boolean important;

    public <T> Convention(Class<T> type, T value, boolean important) {
        super(type, value);
        this.important = important;
    }

    public <T> Convention(Class<T> type, T value) {
        super(type, value);
    }

    public Convention(Object typedValue, boolean important) {
        super(typedValue);
        this.important = important;
    }

    public Convention(Object typedValue) {
        super(typedValue);
    }

    public Convention(String id, Object value, boolean important) {
        super(id, value);
        this.important = important;
    }

    public Convention(String id, Object value) {
        super(id, value);
    }

    @Override
    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

}
