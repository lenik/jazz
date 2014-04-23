package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ReflectTypeProvider
        extends AbstractTypeProvider {

    public static final int PRIORITY = 200;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IType loadType(Class<?> clazz, Object obj, int infoset, ClassDoc classDoc) {
        return new ReflectType(clazz, infoset, classDoc);
    }

}
