package net.bodz.bas.cli;

public class CallInfo {

    public Object[] parameters;
    public Object returnValue;

    public CallInfo(int parameterCount) {
        parameters = new Object[parameterCount];
    }

}
