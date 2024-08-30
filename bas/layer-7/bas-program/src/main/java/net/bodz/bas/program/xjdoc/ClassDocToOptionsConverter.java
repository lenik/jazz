package net.bodz.bas.program.xjdoc;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.StrFn;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.bean.BeanProperty;
import net.bodz.bas.potato.provider.bean.BeanTypeProvider;
import net.bodz.bas.potato.provider.reflect.FieldProperty;
import net.bodz.bas.potato.provider.reflect.ReflectTypeProvider_declared;
import net.bodz.bas.potato.provider.reflect.ReflectType_declared;
import net.bodz.bas.program.model.FieldOption;
import net.bodz.bas.program.model.IMutableOptionGroup;
import net.bodz.bas.program.model.IOptionGroup;
import net.bodz.bas.program.model.MethodOption;
import net.bodz.bas.program.model.MutableOptionGroup;
import net.bodz.bas.program.model.PropertyOption;
import net.bodz.bas.program.model.SyntaxUsage;
import net.bodz.bas.t.coll.IContainer;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.tagtype.StringMapTag;
import net.bodz.mda.xjdoc.util.MethodId;

public class ClassDocToOptionsConverter
        implements
            IMapEntryLoader<Class<?>, IOptionGroup> {

    static final Logger logger = LoggerFactory.getLogger(ClassDocToOptionsConverter.class);

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
            if (! superclass.getName().startsWith("java.")) {
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
        iString _name = StrFn.conv(classDoc.getTag("name"));
        if (_name != null)
            group.setLabel(_name);

        iString text = classDoc.getText();
        if (text == null)
            throw new NullPointerException("text");
        group.setDescription(text.headPar());
        group.setHelpDoc(text.tailPar());

        // Import syntax usages into group.
        StringMapTag usageTag = classDoc.getTag("usage");
        if (usageTag != null) {
            IContainer<String> usageMap = usageTag.getContainer();
            for (String id : usageMap.names()) {
                String script = usageMap.get(id);
                if (script == null) {
                    logger.warn("null script for usage entry of key " + id);
                    continue;
                }
                SyntaxUsage usage = new SyntaxUsage(id, script);
                group.addUsage(usage);
            }
        }

        Set<Method> usedMethods = new HashSet<>();

        if (includeProperties) {
            // int flags = Introspector.USE_ALL_BEANINFO;
            Class<?> stopClass = null;
            if (inheritance != OptionGroupInheritance.flatten)
                stopClass = clazz.getSuperclass();

            IBeanInfo beanInfo;
            try {
                beanInfo = Introspectors.getBeanInfo(clazz, stopClass/* , flags */);
            } catch (IntrospectionException e) {
                throw new ParseException(e.getMessage(), e);
            }

            IType beanType = BeanTypeProvider.getInstance().loadType(beanInfo);

            for (IPropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                BeanProperty property = (BeanProperty) beanType//
                        .getProperty(propertyDescriptor.getName());

                Method getter = propertyDescriptor.getReadMethod();
                Method setter = propertyDescriptor.getWriteMethod();
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
            ReflectType_declared type = //
                    (ReflectType_declared) ReflectTypeProvider_declared.getInstance()//
                            .loadType(clazz);

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (! isIncluded(field))
                    continue;

                if (includeNonPublic)
                    field.setAccessible(true);

                FieldDoc fieldDoc = classDoc.getFieldDoc(field.getName());
                String descriptor = getOptionDescriptor(fieldDoc);
                if (descriptor == null)
                    continue;

                FieldProperty fieldProperty = (FieldProperty) type.getProperty(field.getName());
                FieldOption option = new FieldOption(fieldProperty, fieldDoc);
                OptionDescriptor.apply(option, descriptor);
                option.setGroup(group);
                group.addOption(option);
            }
        }

        if (includeMethods) {
            IType type = ReflectTypeProvider_declared.getInstance().loadType(clazz);

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (! isIncluded(method))
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

                MethodOption option = new MethodOption(type, method, methodDoc);
                OptionDescriptor.apply(option, descriptor);
                option.setGroup(group);
                group.addOption(option);
            }
        }

        return group;
    }

    boolean isIncluded(Member member) {
        int modifiers = member.getModifiers();

        if (! includeNonPublic)
            if (! Modifier.isPublic(modifiers))
                return false;

        if (! includeStatic)
            if (Modifier.isStatic(modifiers))
                return false;

        return true;
    }

    String getOptionDescriptor(IElementDoc doc) {
        if (doc == null)
            return xjdocRequired ? null : "";

        String descriptor = doc.getString("option");
        if (descriptor == null)
            if (! xjdocRequired)
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
