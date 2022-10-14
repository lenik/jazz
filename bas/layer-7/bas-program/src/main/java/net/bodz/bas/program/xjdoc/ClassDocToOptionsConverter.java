package net.bodz.bas.program.xjdoc;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.program.model.*;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.IntrospectionException;
import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyDescriptor;

public class ClassDocToOptionsConverter
        implements
            IMapEntryLoader<Class<?>, IOptionGroup> {

    boolean xjdocRequired = true;

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
    public boolean isXjdocRequired() {
        return xjdocRequired;
    }

    public void setXjdocRequired(boolean xjdocRequired) {
        this.xjdocRequired = xjdocRequired;
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

    public IMutableOptionGroup convertTree(Class<?> clazz)
            throws ParseException {
        return convertTree(clazz, null);
    }

    /**
     * Scan options from a class.
     */
    public IMutableOptionGroup convertTree(Class<?> clazz, IMutableOptionGroup parent)
            throws ParseException {

        if (includeInterfaces) {
            for (Class<?> iface : clazz.getInterfaces()) {
                parent = convertTree(iface, parent);
                parent = compact(parent);
            }
        }

        if (includeSuperclass) {
            Class<?> superclass = clazz.getSuperclass();
            if (!superclass.getName().startsWith("java.")) {
                parent = convertTree(superclass, parent);
                parent = compact(parent);
            }
        }

        IMutableOptionGroup group = convert(clazz, parent);
        return group;
    }

    /**
     * parent = parent.parent...parent
     */
    IMutableOptionGroup compact(IMutableOptionGroup group) {
        if (inheritance == OptionGroupInheritance.reduceEmptyParents)
            // parent = parent.parent...parent
            while (group.getLocalOptionMap().isEmpty()) {
                group = group.getParent();
                if (group == null)
                    break;
            }
        return group;
    }

    public IMutableOptionGroup convert(Class<?> clazz, IMutableOptionGroup parent)
            throws ParseException {
        ClassDoc classDoc;
        try {
            classDoc = Xjdocs.getDefaultProvider().getClassDoc(clazz);
        } catch (XjdocLoaderException e) {
            throw new ParseException(e.getMessage(), e);
        }

        if (classDoc == null)
            classDoc = new ClassDoc(Xjdocs.getDefaultTagLibrary(), clazz.getName());

        return convert(clazz, classDoc, parent);
    }

    IMutableOptionGroup convert(Class<?> clazz, ClassDoc classDoc, IMutableOptionGroup parent)
            throws ParseException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (classDoc == null)
            throw new NullPointerException("classDoc");

        IMutableOptionGroup group = null;
        if (inheritance == OptionGroupInheritance.flatten)
            group = parent;

        if (group == null)
            group = new MutableOptionGroup(parent, clazz);

        // In flatten-mode: override parent's name/description/docs.
        iString _name = iString.fn.val(classDoc.getTag("name"));
        if (_name != null)
            group.setLabel(_name);

        iString text = classDoc.getText();
        if (text == null)
            throw new NullPointerException("text");
        group.setDescription(text.headPar());
        group.setHelpDoc(text.tailPar());

        // Import syntax usages into group.
        @SuppressWarnings("unchecked")
        Map<String, String> usageMap = (Map<String, String>) classDoc.getTag("usage");
        if (usageMap != null)
            for (Entry<String, String> entry : usageMap.entrySet()) {
                String id = entry.getKey();
                String script = entry.getValue();
                if (script == null)
                    throw new NullPointerException("script");
                SyntaxUsage usage = new SyntaxUsage(id, script);
                group.addUsage(usage);
            }

        Set<Method> usedMethods = new HashSet<>();

        if (includeProperties) {
            // int flags = Introspector.USE_ALL_BEANINFO;
            Class<?> stopClass = null;
            if (inheritance != OptionGroupInheritance.flatten)
                stopClass = clazz.getSuperclass();

            BeanInfo beanInfo;
            try {
                beanInfo = Introspector.getBeanInfo(clazz, stopClass/* , flags */);
            } catch (IntrospectionException e) {
                throw new ParseException(e.getMessage(), e);
            }
            for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
                Method getter = property.getReadMethod();
                Method setter = property.getWriteMethod();
                List<Method> accessors = new ArrayList<>();
                if (getter != null)
                    accessors.add(getter);
                if (setter != null)
                    accessors.add(setter);

                for (Method accessor : accessors) {
                    MethodId id = new MethodId(accessor);
                    MethodDoc doc = classDoc.getMethodDoc(id);

                    String descriptor = getOptionDescriptor(doc);
                    if (descriptor != null) {
                        PropertyOption option = new PropertyOption(property, doc);
                        OptionDescriptor.apply(option, descriptor);
                        option.setGroup(group);
                        group.addOption(option);
                        usedMethods.add(accessor);
                        break;
                    }
                }
            }
        }

        if (includeFields) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!isIncluded(field))
                    continue;

                if (includeNonPublic)
                    field.setAccessible(true);

                FieldDoc fieldDoc = classDoc.getFieldDoc(field.getName());
                String descriptor = getOptionDescriptor(fieldDoc);
                if (descriptor == null)
                    continue;

                FieldOption option = new FieldOption(field, fieldDoc);
                OptionDescriptor.apply(option, descriptor);
                option.setGroup(group);
                group.addOption(option);
            }
        }

        if (includeMethods) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!isIncluded(method))
                    continue;
                if (usedMethods.contains(method))
                    continue;

                if (includeNonPublic)
                    method.setAccessible(true);

                MethodId methodId = new MethodId(method);
                MethodDoc methodDoc = classDoc.getMethodDoc(methodId);

                String descriptor = getOptionDescriptor(methodDoc);
                if (descriptor == null)
                    continue;

                MethodOption option = new MethodOption(method, methodDoc);
                OptionDescriptor.apply(option, descriptor);
                option.setGroup(group);
                group.addOption(option);
            }
        }

        return group;
    }

    boolean isIncluded(Member member) {
        int modifiers = member.getModifiers();

        if (!includeNonPublic)
            if (!Modifier.isPublic(modifiers))
                return false;

        if (!includeStatic)
            if (Modifier.isStatic(modifiers))
                return false;

        return true;
    }

    String getOptionDescriptor(IElementDoc doc) {
        if (doc == null)
            return xjdocRequired ? null : "";

        String descriptor = (String) doc.getTag("option");
        if (descriptor == null)
            if (!xjdocRequired)
                return "";

        return descriptor;
    }

    /** ⇱ Implementation Of {@link IMapEntryLoader}. */
    /* _____________________________ */static section.iface __ENTRY_LOADER__;

    @Override
    public IOptionGroup loadValue(Class<?> clazz)
            throws LazyLoadException {
        IOptionGroup options;
        try {
            options = convertTree(clazz);
        } catch (ParseException e) {
            throw new LazyLoadException(e.getMessage(), e);
        }
        return options;
    }

}
