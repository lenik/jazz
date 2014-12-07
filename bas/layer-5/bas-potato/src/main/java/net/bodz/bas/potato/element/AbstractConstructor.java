package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.mda.xjdoc.model.IElementDoc;

public abstract class AbstractConstructor
        extends AbstractPotatoElement
        implements IConstructor {

    public AbstractConstructor(Class<?> declaringType, IElementDoc doc) {
        super(declaringType, null, doc);
    }

    @Override
    public MethodSignature getSignature() {
        // String methodName = "<ctor>";
        String methodName = getDeclaringClass().getSimpleName();
        Class<?>[] parameterTypes = getParameterTypes();
        MethodSignature signature = new MethodSignature(methodName, parameterTypes);
        return signature;
    }

}
