package net.bodz.bas.commons.controlflow;

public class ControlBreak
        extends ControlGoto {

    private static final long serialVersionUID = -732592070241912407L;

    public ControlBreak() {
        super(null);
    }

    public ControlBreak(Object label) {
        super(label);
    }

    @Override
    public Object getLabel() {
        return this.getDetail();
    }

}
