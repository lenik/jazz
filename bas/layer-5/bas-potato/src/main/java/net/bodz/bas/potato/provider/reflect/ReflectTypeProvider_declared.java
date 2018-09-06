package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ReflectTypeProvider_declared
        extends AbstractTypeProvider {

    public static final int PRIORITY = 299;
    public static final int I_Default = -1;

    public ReflectTypeProvider_declared(int infoset) {
        super(infoset);
    }

    public ReflectTypeProvider_declared(int withInfo, int withoutInfo) {
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

        return new ReflectType_declared(clazz, infoset, false, classDoc);
    }

    static ReflectTypeProvider_declared instance = new ReflectTypeProvider_declared(0, 0);

    public static ReflectTypeProvider_declared getInstance() {
        return instance;
    }

}
