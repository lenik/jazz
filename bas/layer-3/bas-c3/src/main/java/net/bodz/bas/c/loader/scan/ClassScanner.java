package net.bodz.bas.c.loader.scan;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.c.object.IdentityObjectSet;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.meta.Implicit;

public class ClassScanner
        extends ResourceScanner {

    static final Logger logger = LoggerFactory.getLogger(ClassScanner.class);

    public static final int SUBCLASSES = 1;
    public static final int IMPLS = 2;
    public static final int ANNOTATED_CLASSES = 4;

    private Set<Class<?>> analyzedClasses = new HashSet<Class<?>>();
    private Set<Class<?>> rootClasses = new HashSet<Class<?>>();
    private Map<Class<?>, Set<Class<?>>> subclassesMap = new HashMap<Class<?>, Set<Class<?>>>();
    private Map<Class<?>, Set<Class<?>>> implsMap = new HashMap<Class<?>, Set<Class<?>>>();
    private Map<Class<?>, Set<Class<?>>> annotatedClassesMap = new HashMap<Class<?>, Set<Class<?>>>();

    // private Map<String, Set<Class<?>>> featureMap = new HashMap<>();

    public ClassScanner(ClassLoader classLoader) {
        super(classLoader, ClassOrDirFileFilter.INSTANCE);
    }

    /**
     * Get all classes whose parent class is the specified class.
     *
     * Only direct subclass is included.
     *
     * @param parentClass
     *            The parent class.
     * @return Non-<code>null</code> set of subclasses. If there is no subclass of the given parent
     *         class, an empty set is returned.
     */
    public synchronized Set<Class<?>> getSubclasses(Class<?> parentClass) {
        Set<Class<?>> subclasses = subclassesMap.get(parentClass);
        if (subclasses == null) {
            subclasses = new HashSet<Class<?>>();
            subclassesMap.put(parentClass, subclasses);
        }
        return subclasses;
    }

    public synchronized Set<Class<?>> getImpls(Class<?> parentClass) {
        Set<Class<?>> impls = implsMap.get(parentClass);
        if (impls == null) {
            impls = new HashSet<Class<?>>();
            implsMap.put(parentClass, impls);
        }
        return impls;
    }

    /**
     * Get all classes with the specific annotation type.
     *
     * @param annotationType
     *            The annotation type.
     * @return Non-<code>null</code> set of classes which is annotated with the specific annotation
     *         type. If no class is annotated with the given annotation type, an empty set is
     *         returned.
     */
    public synchronized Set<Class<?>> getAnnotatedClasses(Class<?> annotationType) {
        Set<Class<?>> annotatedClasses = annotatedClassesMap.get(annotationType);
        if (annotatedClasses == null) {
            annotatedClasses = new HashSet<Class<?>>();
            annotatedClassesMap.put(annotationType, annotatedClasses);
        }
        return annotatedClasses;
    }

    public Set<Class<?>> getDerivations(Class<?> base, int selection) {
        Set<Class<?>> derivations = new HashSet<Class<?>>();
        findDerivations(derivations, base, selection);
        return derivations;
    }

    void findDerivations(Set<Class<?>> results, Class<?> base, int selection) {
        if ((selection & SUBCLASSES) != 0) {
            // assert !base.isAnnotation();
            for (Class<?> subclass : getSubclasses(base)) {
                if (results.add(subclass))
                    findDerivations(results, subclass, selection);
            }
        }

        if ((selection & IMPLS) != 0) {
            // assert base.isInterface();
            for (Class<?> impl : getImpls(base)) {
                if (results.add(impl))
                    findDerivations(results, impl, selection);
            }
        }

        if ((selection & ANNOTATED_CLASSES) != 0) {
            Set<Class<?>> annotatedSet = annotatedClassesMap.get(base);
            if (annotatedSet == null)
                return;

            if (!base.isAnnotation() && !Collections.isEmpty(annotatedSet))
                throw new IllegalUsageException(String.format(//
                        "A non-annotation %s is used to annotate on: %s", base, annotatedSet));

            int sel = selection;
            boolean inheritedAnnotation = base.isAnnotationPresent(Inherited.class);
            if (!inheritedAnnotation) {
                sel &= ~SUBCLASSES;
                sel &= ~IMPLS;
            }

            boolean explicit = !base.isAnnotationPresent(Implicit.class);
            if (explicit)
                sel &= ~ANNOTATED_CLASSES;

            for (Class<?> item : annotatedSet)
                if (results.add(item)) {
                    if (!item.isAnnotation())
                        findDerivations(results, item, sel);
                }
        }
    }

    /**
     * Analyze the given class and all its parents, interfaces, annotations.
     *
     * @retu Modification counter.
     */
    public synchronized int analyze(Class<?> clazz) {
        if (!analyzedClasses.add(clazz))
            return 0;
        return parseClass(clazz);
    }

    private int parseClass(Class<?> clazz) {
        int counter = 0;

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            counter += addSuperclass(clazz, superclass);
        } else
            rootClasses.add(clazz);

        for (Class<?> iface : clazz.getInterfaces())
            counter += addImpls_rec(clazz, iface);

        // clazz.getDeclaredAnnotations()
        for (Annotation annotation : clazz.getAnnotations())
            counter += addAnnotation(clazz, annotation);

        return counter;
    }

    int addSuperclass(Class<?> clazz, Class<?> superclass) {
        int counter = 0;
        Set<Class<?>> subclasses = getSubclasses(superclass);
        if (subclasses.add(clazz))
            counter++;

        counter += analyze(superclass);
        return counter;
    }

    int addImpls_rec(Class<?> clazz, Class<?> iface) {
        int counter = 0;
        Set<Class<?>> impls = getImpls(iface);
        if (impls.add(clazz))
            counter++;

        for (Class<?> _iface_sup : iface.getInterfaces())
            counter += addImpls_rec(clazz, _iface_sup);

        counter += analyze(iface); // iface is also parsed in full-scan.
        return counter;
    }

    int addAnnotation(Class<?> clazz, Annotation annotation) {
        int counter = 0;
        Class<? extends Annotation> annotationType = annotation.annotationType();
        Set<Class<?>> annotatedSet = getAnnotatedClasses(annotationType);
        if (annotatedSet.add(clazz))
            counter++;

        // option:
        // @A @interface B, and @B class C, then @A class C.

        // annotationType is also analyzed in full-scan.
        // counter += parseClass(annotationType);
        return counter;
    }

    class ResolveTypeAdapter
            implements ITypeNameCallback {

        final ITypeCallback typeCallback;

        public ResolveTypeAdapter(ITypeCallback typeCallback) {
            if (typeCallback == null)
                throw new NullPointerException("typeCallback");
            this.typeCallback = typeCallback;
        }

        @Override
        public boolean typeName(String fqcn) {
            Class<?> clazz;
            try {
                clazz = Class.forName(fqcn, false, getClassLoader());
            } catch (ClassNotFoundException e) {
                logger.error("Failed to resolve class: " + fqcn);
                return false;
            } catch (NoClassDefFoundError e) {
                logger.error("Failed to resolve class: " + fqcn);
                return false;
            }
            return typeCallback.type(clazz);
        }
    }

    public void scanTypes(String packageName, final ITypeCallback typeCallback)
            throws IOException {
        ResolveTypeAdapter typeResolver = new ResolveTypeAdapter(typeCallback);
        scanTypeNames(packageName, typeResolver);
    }

    public void scanTypeNames(String packageName, final ITypeNameCallback callback)
            throws IOException {
        setFilter(ClassOrDirFileFilter.INSTANCE);
        String packageDir = packageName.replace('.', '/');
        Map<String, List<URL>> resources = scanResources(packageDir);
        for (String resourceName : resources.keySet()) {
            assert resourceName.endsWith(".class");
            String rawName = resourceName.substring(0, resourceName.length() - 6);
            String fqcn = rawName.replace('/', '.');

            int dollar = fqcn.lastIndexOf('$');
            if (dollar != -1) {
                String tail = fqcn.substring(dollar + 1);
                if (StringPred.isDecimal(tail)) {
                    // Ignore anonymous inner classes.
                    continue;
                }
            }

            // fqcn = fqcn.replace('$', '.');
            if (!callback.typeName(fqcn))
                // break;
                ;
        }
    }

    public void scan(String packageName)
            throws IOException {
        scanTypes(packageName, new ITypeCallback() {
            @Override
            public boolean type(Class<?> clazz) {
                int addCount = parseClass(clazz);
                return addCount > 0;
            }
        });
    }

    public int size() {
        return subclassesMap.size();
    }

    public int size2() {
        int total = 0;
        for (Set<Class<?>> subclasses : subclassesMap.values())
            total += subclasses.size();
        return total;
    }

    public void dump() {
        for (Class<?> root : rootClasses)
            dump("^", root, "", -1);
    }

    public void dump(Class<?> root) {
        dump(root, -1);
    }

    public void dump(Class<?> root, int selection) {
        dump("^", root, "", selection);
    }

    void dump(String type, Class<?> clazz, String prefix, int selection) {
        new Dumper(selection).dump(type, prefix, clazz);
    }

    class Dumper {

        int selection;
        IdentityObjectSet once = new IdentityObjectSet();
        PrintStream out = System.out;

        public Dumper(int selection) {
            this.selection = selection;
        }

        void dump(String type, String prefix, Class<?> clazz) {
            boolean dumped = !once.add(clazz);

            StringBuilder line = new StringBuilder();
            line.append(prefix);
            line.append(type);
            line.append(" ");
            if (dumped)
                line.append("[ ");
            if (clazz.isAnnotation())
                line.append('@');
            line.append(clazz.getCanonicalName());
            if (dumped)
                line.append(" ...]");

            out.println(line);
            if (dumped)
                return;

            prefix += "    ";

            if ((selection & SUBCLASSES) != 0) {
                Set<Class<?>> subclasses = subclassesMap.get(clazz);
                if (subclasses != null)
                    for (Class<?> subclass : subclasses) {
                        dump("<-- ", prefix, subclass);
                    }
            }

            if ((selection & IMPLS) != 0) {
                Set<Class<?>> impls = implsMap.get(clazz);
                if (impls != null)
                    for (Class<?> impl : impls) {
                        dump("o-- ", prefix, impl);
                    }
            }

            if ((selection & ANNOTATED_CLASSES) != 0) {
                Set<Class<?>> annotatedClasses = annotatedClassesMap.get(clazz);
                if (annotatedClasses != null)
                    for (Class<?> annotatedClass : annotatedClasses)
                        dump("@-- ", prefix, annotatedClass);
            }
        }

    }

    public void dumpStat() {
        for (Entry<Class<?>, Set<Class<?>>> entry : subclassesMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " entries");
        }
    }

    public static ClassScanner getInstance(ClassLoader classLoader) {
        return new ClassScanner(classLoader);
    }

}
