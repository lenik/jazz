package net.bodz.bas.cli.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class ClassOptionParser {

    boolean inherits = true;
    boolean interfaces;

    boolean scanFields = true;
    boolean scanProperties = true;
    boolean scanMethods = true;

    boolean reduceEmptyParents = true;

    public IOptionGroup parse(Class<?> clazz) {
        IOptionGroup parent = null;
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            parent = parse(superclass);
            if (reduceEmptyParents)
                while (parent.getOptions().isEmpty()) {
                    parent = parent.getParent();
                    if (parent == null)
                        break;
                }
        }
        return parse(clazz, null, parent);
    }

    public IOptionGroup parse(Class<?> clazz, ClassDoc classDoc, IOptionGroup parent)
            throws IntrospectionException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        IOptionGroup group = new DefaultOptionGroup(parent, clazz);

        if (scanFields) {
            classDoc.getFieldDocs();
        }

        if (scanMethods) {
            Map<MethodId, MethodDoc> methodDocs = classDoc.getMethodDocs();
        }

        if (scanProperties) {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
                Method getter = property.getReadMethod();
                MethodId getterId = new MethodId(getter);
                MethodDoc getterDoc = classDoc.getMethodDoc(getterId);
            }
        }
        return group;
    }

}
