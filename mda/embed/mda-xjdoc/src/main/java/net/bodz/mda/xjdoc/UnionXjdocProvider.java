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
public class UnionXjdocProvider
        extends AbstractXjdocProvider {

    Set<IXjdocProvider> loaders;
    Map<Class<?>, ClassDoc> cache;

    public UnionXjdocProvider() {
        loaders = new TreeSet<IXjdocProvider>(PriorityComparator.INSTANCE);
        for (IXjdocProvider loader : ServiceLoader.load(IXjdocProvider.class))
            loaders.add(loader);

        cache = new WeakHashMap<>();
    }

    /**
     * Query the cache or re-load from the providers.
     * 
     * @param clazz
     *            The class whose class-doc will be returned.
     * @return <code>null</code> if no available class doc.
     */
    public ClassDoc getClassDoc(Class<?> clazz)
            throws XjdocLoaderException {
        if (clazz == null)
            throw new NullPointerException("clazz");

        ClassDoc classDoc = cache.get(clazz);
        if (classDoc == null)
            classDoc = loadClassDoc(clazz);

        return classDoc;
    }

    @Override
    public ClassDoc loadClassDoc(Class<?> clazz)
            throws XjdocLoaderException {
        for (IXjdocProvider loader : loaders) {
            ClassDoc classDoc = loader.getClassDoc(clazz);
            if (classDoc != null) {
                cache.put(clazz, classDoc);
                return classDoc;
            }
        }
        return null;
    }

    private static UnionXjdocProvider instance = new UnionXjdocProvider();

    public static UnionXjdocProvider getInstance() {
        return instance;
    }

}
