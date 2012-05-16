package net.bodz.bas.lang.negotiation;

public class MandatoryOption
        extends AbstractParameter {

    private static final long serialVersionUID = 1L;

    public MandatoryOption(Class<?> type, Object value) {
        super(type, value);
    }

    public MandatoryOption(Object typedValue) {
        super(typedValue);
    }

    public MandatoryOption(String id, Object value) {
        super(id, value);
    }

    @Override
    public boolean isImportant() {
        return true;
    }

}
