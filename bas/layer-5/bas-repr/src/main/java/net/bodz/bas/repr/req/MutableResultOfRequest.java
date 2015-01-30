package net.bodz.bas.repr.req;

public class MutableResultOfRequest
        implements IResultOfRequest {

    private Object target;
    private Throwable exception;
    private IMethodOfRequest nextMethod;

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public IMethodOfRequest getNextMethod() {
        return nextMethod;
    }

    @Override
    public void setNextMethod(IMethodOfRequest nextMethod) {
        this.nextMethod = nextMethod;
    }

}
