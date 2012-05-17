package net.bodz.bas.disp.req;


public class DefaultRequestResult
        implements IRequestResult {

    private Object target;
    private Throwable exception;
    private IRequestMethod nextMethod;

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
    public IRequestMethod getNextMethod() {
        return nextMethod;
    }

    @Override
    public void setNextMethod(IRequestMethod nextMethod) {
        this.nextMethod = nextMethod;
    }

}
