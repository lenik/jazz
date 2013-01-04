package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class DeclaredTypeProvider
        extends AbstractTypeProvider {

    public static final int PRIORITY = 299;

    int visibilities = DeclaredMembers.PRIVATE | DeclaredMembers.PACKAGE_PRIVATE | DeclaredMembers.PROTECTED;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IType getType(Class<?> clazz, Object obj, int infoset, ClassDoc classDoc) {
        return new DeclaredMembers(clazz, infoset | visibilities, classDoc, false);
    }

}
