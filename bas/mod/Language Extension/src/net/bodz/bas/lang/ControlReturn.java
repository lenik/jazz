package net.bodz.bas.lang;


public class ControlReturn extends Control {

    private static final long serialVersionUID = 7374615167936405650L;

    public ControlReturn() {
        super();
    }

    public ControlReturn(Object returnValue) {
        super(returnValue);
    }

    public Object getReturnValue() {
        return getDetail();
    }

}
