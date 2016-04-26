package net.bodz.bas.repr.path;

public class NprWrapper
        implements INoPathRef {

    Object target;

    public NprWrapper(Object target) {
        this.target = target;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    public static INoPathRef wrap(Object o) {
        return new NprWrapper(o);
    }

}
