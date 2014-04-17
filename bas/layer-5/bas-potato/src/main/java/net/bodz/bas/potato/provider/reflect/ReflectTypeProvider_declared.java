package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ReflectTypeProvider_declared
        extends AbstractTypeProvider {

    public static final int PRIORITY = 299;

    int visibilities = ReflectType_declared.PRIVATE //
            | ReflectType_declared.PACKAGE_PRIVATE //
            | ReflectType_declared.PROTECTED;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IType getType(Class<?> clazz, Object obj, int infoset, ClassDoc classDoc) {
        return new ReflectType_declared(clazz, infoset | visibilities, classDoc, false);
    }

}
