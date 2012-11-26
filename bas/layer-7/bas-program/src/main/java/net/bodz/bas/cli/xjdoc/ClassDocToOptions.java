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
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.JavaElementDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;

public class ClassDocToOptions {

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

    public IEditableOptionGroup convertTree(Class<?> clazz)
            throws ParseException {
        return convertTree(clazz, null);
    }

    /**
     * Scan options from a class.
     */
    public IEditableOptionGroup convertTree(Class<?> clazz, IEditableOptionGroup parent)
            throws ParseException {

        if (includeInterfaces) {
            for (Class<?> iface : clazz.getInterfaces()) {
                parent = convert(iface, parent);
                parent = compact(parent);
            }
        }

        if (includeSuperclass) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                parent = convert(superclass, parent);
                parent = compact(parent);
            }
        }

        IEditableOptionGroup group = convert(clazz, parent);
        return group;
    }

    /**
     * parent = parent.parent...parent
     */
    IEditableOptionGroup compact(IEditableOptionGroup group) {
        if (inheritance == OptionGroupInheritance.reduceEmptyParents)
            // parent = parent.parent...parent
            while (group.getLocalOptionMap().isEmpty()) {
                group = group.getParent();
                if (group == null)
                    break;
            }
        return group;
    }

    public IEditableOptionGroup convert(Class<?> clazz, IEditableOptionGroup parent)
            throws ParseException {
        ClassDoc classDoc;
        try {
            classDoc = ClassDocs.loadFromResource(clazz, true);

        } catch (ClassDocLoadException e) {
            throw new ParseException(e.getMessage(), e);
        }
        return convert(clazz, classDoc, parent);
    }

    IEditableOptionGroup convert(Class<?> clazz, ClassDoc classDoc, IEditableOptionGroup parent)
            throws ParseException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (classDoc == null)
            throw new NullPointerException("classDoc");

        IEditableOptionGroup group = null;
        if (inheritance == OptionGroupInheritance.flatten)
            group = parent;

        if (group == null)
            group = new TransientOptionGroup(parent, clazz);

        // In flatten-mode: override parent's name/description/docs.
        DomainString _name = (DomainString) classDoc.getTag("name");
        if (_name != null)
            group.setDisplayName(_name);

        DomainString text = classDoc.getText();
        if (text != null) {
            group.setDescription(text.headPar());
            group.setHelpDoc(text.tailPar());
        }

        // Import syntax usages into group.
        Map<String, SyntaxUsage> usageMap = (Map<String, SyntaxUsage>) classDoc.getTag("usage");
        if (usageMap != null)
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

                if (convertElement(option, fieldDoc))
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

                if (convertElement(option, methodDoc))
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

                if (convertElement(option, getterDoc))
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

    /**
     * @return <code>false</code> if nothing done.
     */
    boolean convertElement(TransientOption option, JavaElementDoc doc)
            throws ParseException {
        if (doc == null)
            return annotatedOnly ? false : true;

        String descriptor = (String) doc.getTag("option");
        if (descriptor == null)
            if (annotatedOnly)
                return false;

        OptionDescriptor.apply(option, descriptor);

        DomainString name = (DomainString) doc.getTag("name");
        if (name != null)
            option.setDisplayName(name);

        // option.setArtifactDoc(doc);
        DomainString header = doc.getText().headPar();
        DomainString body = doc.getText().tailPar();
        option.setDescription(header);
        option.setHelpDoc(body);

        return true;
    }

}
