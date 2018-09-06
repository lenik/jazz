package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ReflectTypeProvider
        extends AbstractTypeProvider {

    public static final int PRIORITY = 200;
    public static final int I_Default = ITypeProvider.I_Default;

    public ReflectTypeProvider(int infoset) {
        super(infoset);
    }

    public ReflectTypeProvider(int withInfo, int withoutInfo) {
        super(withInfo, withoutInfo);
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public int getDefaultInfoset() {
        return I_Default;
    }

    @Override
    public IType loadType(Class<?> clazz, Object obj, int infoset) {
        ClassDoc classDoc = null;
        if ((infoset & ITypeProvider.I_Docs) != 0)
            classDoc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(clazz);
        return new ReflectType(clazz, infoset, classDoc);
    }

    static ReflectTypeProvider instance = new ReflectTypeProvider(0, 0);

    public static ReflectTypeProvider getInstance() {
        return instance;
    }

}
