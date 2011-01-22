package net.bodz.swt.gui;

import java.lang.reflect.Constructor;

import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.lang.util.Reflects;

public class CtorObject extends CallContext {

    private final Constructor<?> ctor;

    // private final Object outer;

    public CtorObject(Constructor<?> ctor) {
        super(ctor.getParameterTypes());
        this.ctor = ctor;
        // this.outer = outer;
    }

    public Constructor<?> getConstructor() {
        return ctor;
    }

    public Object newInstance() throws ReflectException {
        Object retval = Reflects.newInstance(ctor, parameters);
        setRetval(retval);
        return retval;
    }

}
