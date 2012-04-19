package net.bodz.bas.loader.scan;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.closure.Pred1;
import net.bodz.bas.collection.set.IdentityHashSet;

public class ClassScanner
        extends ResourceScanner {

    public static final int SUBCLASSES = 1;
    public static final int ANNOTATED_CLASSES = 2;

    Set<Class<?>> parsedClasses = new HashSet<Class<?>>();
    Set<Class<?>> rootClasses = new HashSet<Class<?>>();
    Map<Class<?>, Set<Class<?>>> subclassesMap = new HashMap<Class<?>, Set<Class<?>>>();
    Map<Class<?>, Set<Class<?>>> annotatedClassesMap = new HashMap<Class<?>, Set<Class<?>>>();

    public ClassScanner() {
        super(ClassOrDirFileFilter.INSTANCE);
    }

    public synchronized Set<Class<?>> getSubclasses(Class<?> clazz) {
        Set<Class<?>> subclasses = subclassesMap.get(clazz);
        if (subclasses == null) {
            subclasses = new HashSet<Class<?>>();
            subclassesMap.put(clazz, subclasses);
        }
        return subclasses;
    }

    public synchronized Set<Class<?>> getAnnotatedClasses(Class<?> annotationType) {
        Set<Class<?>> annotatedClasses = annotatedClassesMap.get(annotationType);
        if (annotatedClasses == null) {
            annotatedClasses = new HashSet<Class<?>>();
            annotatedClassesMap.put(annotationType, annotatedClasses);
        }
        return annotatedClasses;
    }

    public Set<Class<?>> getClosure(Class<?> clazz) {
        return getClosure(clazz, -1);
    }

    public Set<Class<?>> getClosure(Class<?> clazz, int selection) {
        Set<Class<?>> closure = new HashSet<Class<?>>();
        _getClosure(clazz, closure, selection);
        return closure;
    }

    void _getClosure(Class<?> clazz, Set<Class<?>> closure, int selection) {
        if (closure.add(clazz)) {
            if ((selection & SUBCLASSES) != 0) {
                // assert !clazz.isAnnotation();
                for (Class<?> subclass : getSubclasses(clazz))
                    _getClosure(subclass, closure, selection);
            }
            if ((selection & ANNOTATED_CLASSES) != 0) {
                // assert clazz.isAnnotation();
                Set<Class<?>> annotatedClasses = annotatedClassesMap.get(clazz);
                // It's very possible that clazz is not an annotation at all.
                if (annotatedClasses != null)
                    for (Class<?> annotatedClass : getAnnotatedClasses(clazz))
                        _getClosure(annotatedClass, closure, selection);
            }
        }
    }

    public synchronized int parse(Class<?> clazz) {
        return _parse(clazz);
    }

    private int _parse(Class<?> clazz) {
        int counter = 0;
        if (parsedClasses.add(clazz)) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                Set<Class<?>> subclasses = getSubclasses(superclass);
                if (subclasses.add(clazz))
                    counter++;
                counter += _parse(superclass);
            } else
                rootClasses.add(clazz);

            for (Class<?> iface : clazz.getInterfaces()) {
                Set<Class<?>> set = getSubclasses(iface);
                if (set.add(clazz))
                    counter++;
                counter += _parse(iface); // iface is also parsed in full-scan.
            }

            for (Annotation annotation : clazz.getAnnotations()) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                Set<Class<?>> set = getAnnotatedClasses(annotationType);
                if (set.add(clazz))
                    counter++;
                // @A @interface B, and @B class C, then @A class C.
                // counter += _parse(annotationType); // annotationType is also parsed in full-scan.
            }
        }
        return counter;
    }

    class TypeResolver
            extends Pred1<String> {

        final Pred1<Class<?>> target;

        public TypeResolver(Pred1<Class<?>> target) {
            if (target == null)
                throw new NullPointerException("target");
            this.target = target;
        }

        @Override
        public boolean test(String fqcn) {
            Class<?> clazz;
            try {
                clazz = Class.forName(fqcn, false, getClassLoader());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            return target.test(clazz);
        }

    }

    public void scanTypes(String packageName, final Pred1<Class<?>> typeCallback)
            throws IOException {
        TypeResolver typeResolver = new TypeResolver(typeCallback);
        scanTypenames(packageName, typeResolver);
    }

    public void scan(String packageName)
            throws IOException {
        scanTypes(packageName, new Pred1<Class<?>>() {
            @Override
            public boolean test(Class<?> clazz) {
                int adds = _parse(clazz);
                return adds > 0;
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
        IdentityHashSet once = new IdentityHashSet();
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
