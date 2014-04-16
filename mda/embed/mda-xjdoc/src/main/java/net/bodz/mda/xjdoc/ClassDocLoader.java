package net.bodz.mda.xjdoc;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;
import java.util.WeakHashMap;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.mda.xjdoc.model.ClassDoc;

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

}
