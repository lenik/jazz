package net.bodz.bas.context;

import net.bodz.bas.jvm.stack.Caller;

public class Contexts {

    public static ClassContext from(Class<?> clazz) {
        return new ClassContext(clazz);
    }

    public static ClassContext caller() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return from(callerClass);
    }

}
