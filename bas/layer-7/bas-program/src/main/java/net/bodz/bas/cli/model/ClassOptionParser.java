package net.bodz.bas.cli.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.conv.ClassDocLoadException;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;

public class ClassOptionParser {

    boolean includeSuperclass = true;
    boolean includeInterfaces = false;
    boolean reduceEmptyParents = true;

    boolean includeFields = true;
    boolean includeMethods = true;
    boolean includeNonPublic = false;
    boolean includeStatic = false;

    boolean includeProperties = true;

    public boolean isIncludeSuperclass() {
        return includeSuperclass;
    }

    public void setIncludeSuperclass(boolean includeSuperclass) {
        this.includeSuperclass = includeSuperclass;
    }

    public boolean isIncludeInterfaces() {
        return includeInterfaces;
    }

    public void setIncludeInterfaces(boolean includeInterfaces) {
        this.includeInterfaces = includeInterfaces;
    }

    public boolean isReduceEmptyParents() {
        return reduceEmptyParents;
    }

    public void setReduceEmptyParents(boolean reduceEmptyParents) {
        this.reduceEmptyParents = reduceEmptyParents;
    }

    public boolean isIncludeFields() {
        return includeFields;
    }

    public void setIncludeFields(boolean includeFields) {
        this.includeFields = includeFields;
    }

    public boolean isIncludeMethods() {
        return includeMethods;
    }

    public void setIncludeMethods(boolean includeMethods) {
        this.includeMethods = includeMethods;
    }

    public boolean isIncludeNonPublic() {
        return includeNonPublic;
    }

    public void setIncludeNonPublic(boolean includeNonPublic) {
        this.includeNonPublic = includeNonPublic;
    }

    public boolean isIncludeStatic() {
        return includeStatic;
    }

    public void setIncludeStatic(boolean includeStatic) {
        this.includeStatic = includeStatic;
    }

    public boolean isIncludeProperties() {
        return includeProperties;
    }

    public void setIncludeProperties(boolean includeProperties) {
        this.includeProperties = includeProperties;
    }

    public IOptionGroup parse(Class<?> clazz)
            throws ParseException {

        IOptionGroup parent = null;

        if (includeInterfaces) {
            for (Class<?> iface : clazz.getInterfaces()) {
                parent = parse(iface, parent);
                parent = compact(parent);
            }
        }

        if (includeSuperclass) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                parent = parse(superclass, parent);
                parent = compact(parent);
            }
        }

        IOptionGroup group = parse(clazz, parent);
        return group;
    }

    /**
     * parent = parent.parent...parent
     */
    IOptionGroup compact(IOptionGroup group) {
        if (reduceEmptyParents)
            // parent = parent.parent...parent
            while (group.getOptions().isEmpty()) {
                group = group.getParent();
                if (group == null)
                    break;
            }
        return group;
    }

    public IOptionGroup parse(Class<?> clazz, IOptionGroup parent)
            throws ParseException {
        ClassDoc classDoc;
        try {
            classDoc = ClassDocs.loadFromResource(clazz);
        } catch (ClassDocLoadException e) {
            throw new ParseException(e.getMessage(), e);
        }
        return parse(clazz, classDoc, parent);
    }

    IOptionGroup parse(Class<?> clazz, ClassDoc classDoc, IOptionGroup parent)
            throws ParseException {
        if (clazz == null)
            throw new NullPointerException("clazz");

        IOptionGroup group = new DefaultOptionGroup(parent, clazz);

        if (includeFields) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!isIncluded(field))
                    continue;

                field.setAccessible(true);

                FieldDoc fieldDoc = classDoc.getFieldDoc(field.getName());
                // ...
            }
        }

        if (includeMethods) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!isIncluded(method))
                    continue;

                method.setAccessible(true);
                MethodDoc methodDoc = classDoc.getMethodDoc(method);
                // ...
            }
        }

        if (includeProperties) {
            BeanInfo beanInfo;
            try {
                beanInfo = Introspector.getBeanInfo(clazz);
            } catch (IntrospectionException e) {
                throw new ParseException(e.getMessage(), e);
            }
            for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
                Method getter = property.getReadMethod();
                
                MethodDoc getterDoc = classDoc.getMethodDoc(getter);
            }
        }
        return group;
    }

    boolean isIncluded(Member member) {
        int modifiers = member.getModifiers();

        if (includeNonPublic)
            if (Modifier.isPublic(modifiers))
                return false;

        if (!includeStatic)
            if (Modifier.isStatic(modifiers))
                return false;

        return true;
    }

}
