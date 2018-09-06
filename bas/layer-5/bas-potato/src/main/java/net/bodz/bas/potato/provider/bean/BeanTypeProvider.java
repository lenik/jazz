package net.bodz.bas.potato.provider.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

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
    public IType loadType(Class<?> clazz, Object obj, int infoset) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

            ClassDoc classDoc = null;
            if ((infoset & ITypeProvider.I_Docs) != 0)
                classDoc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(clazz);
            else
                classDoc = ClassDoc.n_a(clazz.getName());

            return new BeanType(beanInfo, infoset, classDoc, UnionXjdocProvider.getInstance());
        } catch (IntrospectionException e) {
            return null;
        }
    }

    static BeanTypeProvider instance = new BeanTypeProvider(0, 0);

    public static BeanTypeProvider getInstance() {
        return instance;
    }

}
