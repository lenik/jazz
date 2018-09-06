package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ReflectTypeProvider
        extends AbstractTypeProvider {

    public static final int PRIORITY = 200;

    public ReflectTypeProvider(int infoset) {
        super(infoset);
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IType loadType(Class<?> clazz, Object obj, int infoset) {
        ClassDoc classDoc = null;
        if ((infoset & ITypeProvider.DOCS) != 0)
            classDoc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(clazz);
        return new ReflectType(clazz, infoset, classDoc);
    }

}
