package net.bodz.bas.c.loader.scan;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.c.object.IdentityObjectSet;
import net.bodz.bas.err.IllegalUsageException;

public class ClassScanner
        extends ResourceScanner {

    public static final int SUBCLASSES = 1;
    public static final int ANNOTATED_CLASSES = 2;

    Set<Class<?>> analyzedClasses = new HashSet<Class<?>>();
    Set<Class<?>> rootClasses = new HashSet<Class<?>>();
    Map<Class<?>, Set<Class<?>>> subclassesMap = new HashMap<Class<?>, Set<Class<?>>>();
    Map<Class<?>, Set<Class<?>>> annotatedClassesMap = new HashMap<Class<?>, Set<Class<?>>>();
    Map<String, Set<Class<?>>> featureMap = new HashMap<String, Set<Class<?>>>();

    public ClassScanner() {
        super(ClassOrDirFileFilter.INSTANCE);
    }

    /**
     * Get all classes whose parent class is the specified class.
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

    /**
     * Get all classes
     */
    public Set<Class<?>> getDerivations(Class<?> base) {
        return getDerivations(base, -1);
    }

    public Set<Class<?>> getDerivations(Class<?> base, int selection) {
        Set<Class<?>> derivations = new HashSet<Class<?>>();
        findDerivations(derivations, base, selection);
        return derivations;
    }

    void findDerivations(Set<Class<?>> markSet, Class<?> base, int selection) {
        if (markSet.add(base)) {
            if ((selection & SUBCLASSES) != 0) {
                // assert !clazz.isAnnotation();
                for (Class<?> subclass : getSubclasses(base))
                    findDerivations(markSet, subclass, selection);
            }
            if ((selection & ANNOTATED_CLASSES) != 0) {
                Set<Class<?>> annotatedSet = annotatedClassesMap.get(base);
                if (annotatedSet == null)
                    return;

                if (!base.isAnnotation() && !Collections.isEmpty(annotatedSet))
                    throw new IllegalUsageException(String.format(//
                            "A non-annotation %s is used to annotate on: ", base, annotatedSet));

                int sel = selection;
                boolean inheritedAnnotation = base.isAnnotationPresent(Inherited.class);
                if (!inheritedAnnotation)
                    sel &= ~SUBCLASSES;

                for (Class<?> annotatedClass : getAnnotatedClasses(base))
                    findDerivations(markSet, annotatedClass, sel);
            }
        }
    }

    /**
     * Analyze the given class and all its parents, interfaces, annotations.
     * 
     * @retu Modification counter.
     */
    public synchronized int analyze(Class<?> clazz) {
        return _analyze(clazz);
    }

    private int _analyze(Class<?> clazz) {
        int counter = 0;
        if (analyzedClasses.add(clazz)) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                Set<Class<?>> subclasses = getSubclasses(superclass);
                if (subclasses.add(clazz))
                    counter++;
                counter += _analyze(superclass);
            } else
                rootClasses.add(clazz);

            for (Class<?> iface : clazz.getInterfaces()) {
                Set<Class<?>> subclasses = getSubclasses(iface);
                if (subclasses.add(clazz))
                    counter++;
                counter += _analyze(iface); // iface is also parsed in full-scan.
            }

            Annotation[] annotations;
            annotations = clazz.getAnnotations();
            // annotations = clazz.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                Set<Class<?>> annotatedSet = getAnnotatedClasses(annotationType);
                if (annotatedSet.add(clazz))
                    counter++;
                // @A @interface B, and @B class C, then @A class C.
                // annotationType is also analyzed in full-scan.
                // counter += _analyze(annotationType);
            }
        }
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
            } catch (ClassNotFoundException | NoClassDefFoundError e) {
                throw new RuntimeException("Failed to resolve class: " + fqcn, e);
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
        for (String resourceName : scanResources(packageDir).keySet()) {
            assert resourceName.endsWith(".class");
            String rawName = resourceName.substring(0, resourceName.length() - 6);
            String fqcn = rawName.replace('/', '.');
            // fqcn = fqcn.replace('$', '.');
            callback.typeName(fqcn);
        }
    }

    public void scan(String packageName)
            throws IOException {
        scanTypes(packageName, new ITypeCallback() {
            @Override
            public boolean type(Class<?> clazz) {
                int addCount = _analyze(clazz);
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
                        dump("-", prefix, subclass);
                    }
            }

            if ((selection & ANNOTATED_CLASSES) != 0) {
                Set<Class<?>> annotatedClasses = annotatedClassesMap.get(clazz);
                if (annotatedClasses != null)
                    for (Class<?> annotatedClass : annotatedClasses)
                        dump("#", prefix, annotatedClass);
            }
        }

    }

    public void dumpStat() {
        for (Entry<Class<?>, Set<Class<?>>> entry : subclassesMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " entries");
        }
    }

    public static ClassScanner SCL = new ClassScanner();

}
