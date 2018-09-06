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

    public BeanTypeProvider(int infoset) {
        super(infoset);
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IType loadType(Class<?> clazz, Object obj, int infoset) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

            ClassDoc classDoc = null;
            if ((infoset & ITypeProvider.DOCS) != 0)
                classDoc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(clazz);
            else
                classDoc = ClassDoc.n_a(clazz.getName());

            return new BeanType(beanInfo, infoset, classDoc, UnionXjdocProvider.getInstance());
        } catch (IntrospectionException e) {
            return null;
        }
    }

}
