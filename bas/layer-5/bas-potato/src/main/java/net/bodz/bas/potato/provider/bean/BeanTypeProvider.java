package net.bodz.bas.potato.provider.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

import net.bodz.bas.potato.AbstractTypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class BeanTypeProvider
        extends AbstractTypeProvider {

    public static final int PRIORITY = 100;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IType getType(Class<?> clazz, Object obj, int infoset, ClassDoc classDoc) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            return new BeanType(beanInfo, infoset, classDoc);
        } catch (IntrospectionException e) {
            return null;
        }
    }

}
