package net.bodz.bas.potato.provider.bean;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.UnionXjdocProvider;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class BeanTypeProvider
        extends AbstractTypeProvider {

    public static final int PRIORITY = 100;

    /**
     * Declared only if not specified.
     */
    public static final int I_DeclaredOnly = 0x0001;
    public static final int I_ReadOnly = 0x0002;
    public static final int I_WriteOnly = 0x0004;

    public static final int I_Default = ITypeProvider.I_Default | I_ReadOnly | I_WriteOnly;

    public BeanTypeProvider(int infoset) {
        super(infoset);
    }

    public BeanTypeProvider(int withInfo, int withoutInfo) {
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
    public BeanType loadType(Class<?> clazz) {
        return (BeanType) super.loadType(clazz);
    }

    @Override
    public BeanType loadType(Class<?> clazz, Object obj, int infoset) {
        try {
            IBeanInfo beanInfo = Introspectors.getBeanInfo(clazz);

            ClassDoc classDoc = null;
            if ((infoset & ITypeProvider.I_Docs) != 0)
                classDoc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(clazz);
            else
                classDoc = ClassDoc.n_a(clazz.getName());

            IType declaringType = getType(clazz.getDeclaringClass());

            return new BeanType(this, declaringType, beanInfo, infoset, classDoc, UnionXjdocProvider.getInstance());
        } catch (IntrospectionException e) {
            return null;
        }
    }

    public BeanType loadType(IBeanInfo beanInfo) {
        return loadType(beanInfo, null, getDefaultInfoset());
    }

    public BeanType loadType(IBeanInfo beanInfo, Object obj, int infoset) {
        Class<?> clazz = beanInfo.getBeanDescriptor().getBeanClass();

        ClassDoc classDoc = null;
        if ((infoset & ITypeProvider.I_Docs) != 0)
            classDoc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(clazz);
        else
            classDoc = ClassDoc.n_a(clazz.getName());

        IType declaringType = getType(clazz.getDeclaringClass());

        return new BeanType(this, declaringType, beanInfo, infoset, classDoc, UnionXjdocProvider.getInstance());
    }

    static BeanTypeProvider instance = new BeanTypeProvider(0, 0);

    public static BeanTypeProvider getInstance() {
        return instance;
    }

}
