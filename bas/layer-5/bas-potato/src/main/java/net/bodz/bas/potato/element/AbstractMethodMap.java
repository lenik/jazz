package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;

public abstract class AbstractMethodMap
        implements IMethodMap {

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(getMethodCount() * 100);
        for (IMethod method : getMethods()) {
            MethodSignature signature = method.getSignature();
            buf.append(signature);
            buf.append("\n");
        }
        return buf.toString();
    }

}
