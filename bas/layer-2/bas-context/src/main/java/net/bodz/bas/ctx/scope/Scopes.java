package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.scope.id.ClassScopeDescriptor;
import net.bodz.bas.jvm.stack.Caller;

public class Scopes {

    public static ClassScopeDescriptor from(Class<?> clazz) {
        return new ClassScopeDescriptor(clazz);
    }

    public static ClassScopeDescriptor caller() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return from(callerClass);
    }

}
