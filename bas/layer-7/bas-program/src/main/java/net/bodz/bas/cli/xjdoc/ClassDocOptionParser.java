package net.bodz.bas.cli.xjdoc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import net.bodz.bas.cli.model.*;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.mda.xjdoc.conv.ClassDocLoadException;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.JavaElementDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;

public class ClassDocOptionParser {

    boolean annotatedOnly = true;

    boolean includeSuperclass = true;
    boolean includeInterfaces = false;
    OptionGroupInheritance inheritance = OptionGroupInheritance.reflective;

    boolean includeFields = true;
    boolean includeMethods = true;
    boolean includeNonPublic = false;
    boolean includeStatic = false;

    boolean includeProperties = true;

    /**
     * Exclude members without &#64;option javadoc annotation.
     */
    public boolean isAnnotatedOnly() {
        return annotatedOnly;
    }

    public void setAnnotatedOnly(boolean annotatedOnly) {
        this.annotatedOnly = annotatedOnly;
    }

    /**
     * Whether to scan superclass for options.
     * 
     * For Bean-Properties:
     * <ul>
     * <li>reflective-mode:
     * <li>reduced-parent-mode:
     * <li>flatten-mode:
     * </ul>
     */
    public boolean isIncludeSuperclass() {
        return includeSuperclass;
    }

    public void setIncludeSuperclass(boolean includeSuperclass) {
        this.includeSuperclass = includeSuperclass;
    }

    /**
     * Whether to scan interfaces for options.
     */
    public boolean isIncludeInterfaces() {
        return includeInterfaces;
    }

    public void setIncludeInterfaces(boolean includeInterfaces) {
        this.includeInterfaces = includeInterfaces;
    }

    /**
     * The option group inheritance mode.
     * 
     * @see OptionGroupInheritance#reflective
     * @see OptionGroupInheritance#reduceEmptyParents
     * @see OptionGroupInheritance#flatten
     */
    public OptionGroupInheritance getInheritance() {
        return inheritance;
    }

    public void setInheritance(OptionGroupInheritance inheritance) {
        this.inheritance = inheritance;
    }

    /**
     * Whether to include the member fields.
     */
    public boolean isIncludeFields() {
        return includeFields;
    }

    public void setIncludeFields(boolean includeFields) {
        this.includeFields = includeFields;
    }

    /**
     * Whether to include the member methods.
     */
    public boolean isIncludeMethods() {
        return includeMethods;
    }

    public void setIncludeMethods(boolean includeMethods) {
        this.includeMethods = includeMethods;
    }

    /**
     * Whether to include private/protected members.
     */
    public boolean isIncludeNonPublic() {
        return includeNonPublic;
    }

    public void setIncludeNonPublic(boolean includeNonPublic) {
        this.includeNonPublic = includeNonPublic;
    }

    /**
     * Whether to include static members.
     */
    public boolean isIncludeStatic() {
        return includeStatic;
    }

    public void setIncludeStatic(boolean includeStatic) {
        this.includeStatic = includeStatic;
    }

    /**
     * Whether to include bean properties.
     */
    public boolean isIncludeProperties() {
        return includeProperties;
    }

    public void setIncludeProperties(boolean includeProperties) {
        this.includeProperties = includeProperties;
    }

    public IOptionGroup parseTree(Class<?> clazz)
            throws ParseException {
        return parseTree(clazz, null);
    }

    /**
     * Scan options from a class.
     */
    public IOptionGroup parseTree(Class<?> clazz, IOptionGroup parent)
            throws ParseException {

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
        if (inheritance == OptionGroupInheritance.reduceEmptyParents)
            // parent = parent.parent...parent
            while (group.getLocalOptionMap().isEmpty()) {
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

        IOptionGroup group = null;
        if (inheritance == OptionGroupInheritance.flatten)
            group = parent;

        if (group == null)
            group = new DefaultOptionGroup(parent, clazz);

        // In flatten-mode: override parent's name/description/docs.
        DomainString _name = (DomainString) classDoc.getTag("name");
        if (_name != null)
            group.setDisplayName(_name);
        DomainString _header = classDoc.getTextHeader();
        DomainString _body = classDoc.getTextBody();
        group.setDescription(_header);
        group.setHelpDoc(_body);

        // Import syntax usages into group.
        Map<String, SyntaxUsage> usageMap = (Map<String, SyntaxUsage>) classDoc.getTag("usage");
        for (SyntaxUsage usage : usageMap.values())
            group.addUsage(usage);

        if (includeFields) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!isIncluded(field))
                    continue;

                if (includeNonPublic)
                    field.setAccessible(true);

                FieldOption option = new FieldOption(field);
                FieldDoc fieldDoc = classDoc.getFieldDoc(field.getName());

                if (parseOptionDoc(option, fieldDoc))
                    group.addOption(option);
            }
        }

        if (includeMethods) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!isIncluded(method))
                    continue;

                if (includeNonPublic)
                    method.setAccessible(true);

                MethodOption option = new MethodOption(method);
                MethodDoc methodDoc = classDoc.getMethodDoc(method);

                if (parseOptionDoc(option, methodDoc))
                    group.addOption(option);
            }
        }

        if (includeProperties) {
            int flags = Introspector.USE_ALL_BEANINFO;
            Class<?> stopClass = null;
            if (inheritance != OptionGroupInheritance.flatten)
                stopClass = clazz.getSuperclass();

            BeanInfo beanInfo;
            try {
                beanInfo = Introspector.getBeanInfo(clazz, stopClass, flags);
            } catch (IntrospectionException e) {
                throw new ParseException(e.getMessage(), e);
            }
            for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
                Method getter = property.getReadMethod();

                PropertyOption option = new PropertyOption(property);
                MethodDoc getterDoc = classDoc.getMethodDoc(getter);

                if (parseOptionDoc(option, getterDoc))
                    group.addOption(option);
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

    boolean parseOptionDoc(AbstractOption option, JavaElementDoc doc)
            throws ParseException {
        if (doc == null)
            return annotatedOnly ? false : true;

        String descriptor = (String) doc.getTag("option");
        if (descriptor != null)
            OptionDescriptor.apply(option, descriptor);
        else if (annotatedOnly)
            return false;

        DomainString name = (DomainString) doc.getTag("name");
        if (name != null)
            option.setDisplayName(name);

        DomainString header = doc.getTextHeader();
        DomainString body = doc.getTextBody();
        option.setDescription(header);
        option.setHelpDoc(body);

        return true;
    }

}
