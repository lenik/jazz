package net.bodz.bas.program.model;

/**
 * @deprecated See {@link net.bodz.bas.potato.invoke.Invocation}
 */
@Deprecated
public class MethodCall {

    public Object[] parameters;
    public Object returnValue;

    public MethodCall(int parameterCount) {
        parameters = new Object[parameterCount];
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

}
