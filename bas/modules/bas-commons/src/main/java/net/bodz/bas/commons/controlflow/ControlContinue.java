package net.bodz.bas.commons.controlflow;

public class ControlContinue
        extends ControlGoto {

    private static final long serialVersionUID = -7156299378640401483L;

    public ControlContinue() {
        super(null);
    }

    public ControlContinue(Object label) {
        super(label);
    }

    @Override
    public Object getLabel() {
        return this.getDetail();
    }

}
