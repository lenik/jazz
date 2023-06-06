package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.mda.xjdoc.model.IElementDoc;

public abstract class AbstractConstructor
        extends AbstractPotatoElement
        implements
            IConstructor {

    public AbstractConstructor(IType declaringType, String name, IElementDoc doc) {
        super(declaringType, name, doc);
    }

    @Override
    public MethodSignature getSignature() {
        // String methodName = "<ctor>";
        Class<?> declaringClass = getDeclaringClass();
        String methodName = declaringClass.getSimpleName();
        Class<?>[] parameterTypes = getParameterTypes();
        MethodSignature signature = new MethodSignature(methodName, parameterTypes);
        return signature;
    }

}
