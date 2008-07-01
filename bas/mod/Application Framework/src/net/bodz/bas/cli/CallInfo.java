package net.bodz.bas.cli;

public class CallInfo {

    public Object[] argv;
    public Object   retval;

    public CallInfo(int argc) {
        argv = new Object[argc];
    }

}
