package net.bodz.bas.ctx.scope;

import net.bodz.bas.jvm.stack.Caller;

public class Scopes {

    public static ClassScopeToken from(Class<?> clazz) {
        return new ClassScopeToken(clazz);
    }

    public static ClassScopeToken caller() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return from(callerClass);
    }

}
