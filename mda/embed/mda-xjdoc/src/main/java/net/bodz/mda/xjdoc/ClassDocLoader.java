package net.bodz.mda.xjdoc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;
import java.util.WeakHashMap;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocAware;

@ExcludedFromIndex
public class ClassDocLoader {

    private static Set<IClassDocLoader> loaders;
    private static Map<Class<?>, ClassDoc> cache;

    static {
        loaders = new TreeSet<IClassDocLoader>(PriorityComparator.INSTANCE);
        for (IClassDocLoader loader : ServiceLoader.load(IClassDocLoader.class))
            loaders.add(loader);

        cache = new WeakHashMap<>();
    }

    /**
     * Query the cache or re-load from the providers.
     *
     * @param clazz
     *            The class whose class-doc will be returned.
     * @return <code>null</code> if no aavailable class doc.
     */
    public static ClassDoc load(Class<?> clazz)
            throws ClassDocLoadException {
        if (clazz == null)
            throw new NullPointerException("clazz");

        ClassDoc classDoc = cache.get(clazz);
        if (classDoc == null) {
            for (IClassDocLoader loader : loaders) {
                classDoc = loader.load(clazz);
                if (classDoc != null) {
                    cache.put(clazz, classDoc);
                    break;
                }
            }
        }
        return classDoc;
    }

    public static String injectFields(Class<?> clazz, boolean includeNonPublic) {
        int modifiers = clazz.getModifiers();
        if (!(includeNonPublic || Modifier.isPublic(modifiers)))
            return "Non-public: " + clazz;

        ClassDoc classDoc = ClassDocLoader.load(clazz);

        for (Entry<String, FieldDoc> entry : classDoc.getFieldDocs().entrySet()) {
            String fieldName = entry.getKey();
            FieldDoc fieldDoc = entry.getValue();

            Field field;
            try {
                field = clazz.getField(fieldName);
            } catch (NoSuchFieldException e) {
                return "No such field: " + fieldName;
            }

            modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers))
                return "Non-static: " + field;

            if (!(includeNonPublic || Modifier.isPublic(modifiers)))
                return "Non-public: " + field;

            Class<?> fieldType = field.getType();
            if (IXjdocAware.class.isAssignableFrom(fieldType))
                try {
                    IXjdocAware xjdocAware = (IXjdocAware) field.get(null);
                    xjdocAware.setXjdoc(fieldDoc);
                } catch (IllegalAccessException e) {
                    return "Can't access field: " + e.getMessage();
                }
        } // for fieldDocs
        return null;
    }

}
