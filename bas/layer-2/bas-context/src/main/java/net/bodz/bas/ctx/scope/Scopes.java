package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.scope.impl.ClassScopeInstance;
import net.bodz.bas.jvm.stack.Caller;

public class Scopes {

    public static ClassScopeInstance from(Class<?> clazz) {
        return new ClassScopeInstance(clazz);
    }

    public static ClassScopeInstance caller() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return from(callerClass);
    }

}
