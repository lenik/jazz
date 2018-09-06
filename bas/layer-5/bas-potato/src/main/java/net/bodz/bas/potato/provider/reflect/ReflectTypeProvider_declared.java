package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ReflectTypeProvider_declared
        extends AbstractTypeProvider {

    public static final int PRIORITY = 299;

    final int visibilities;
    static int defaultVisibilities = ReflectType_declared.PUBLIC//
            | ReflectType_declared.PRIVATE //
            | ReflectType_declared.PACKAGE_PRIVATE //
            | ReflectType_declared.PROTECTED;

    public ReflectTypeProvider_declared(int infoset) {
        this(infoset, defaultVisibilities);
    }

    public ReflectTypeProvider_declared(int infoset, int visibilities) {
        super(infoset);
        this.visibilities = visibilities;
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

        return new ReflectType_declared(clazz, infoset | visibilities, false, classDoc);
    }

    static ReflectTypeProvider_declared instance = new ReflectTypeProvider_declared(-1);

    public static ReflectTypeProvider_declared getInstance() {
        return instance;
    }

}
