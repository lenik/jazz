package net.bodz.bas.lang;

public class ControlReturn
        extends ControlBreak {

    private static final long serialVersionUID = 1L;

    private final Object value;

    public ControlReturn() {
        super();
        this.value = null;
    }

    public ControlReturn(Object value) {
        super();
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

}
