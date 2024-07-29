package net.bodz.bas.c.loader.scan;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.c.object.IdentityObjectSet;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;
import net.bodz.bas.meta.meta.Implicit;

public class ClassForrest
        implements
            Iterable<Class<?>> {

    Set<Class<?>> parseList = new LinkedHashSet<>();
    Set<Class<?>> closure = new HashSet<>();

    Set<Class<?>> roots = new HashSet<>(); // superclass is Object.
    Map<Class<?>, ClassExt> extensions = new HashMap<>();

    public Set<Class<?>> getParseList() {
        return parseList;
    }

    public Set<Class<?>> getRoots() {
        return roots;
    }

    public Set<Class<?>> getClasses() {
        return extensions.keySet();
    }

    @Override
    public Iterator<Class<?>> iterator() {
        return extensions.keySet().iterator();
    }

    public void clear() {
        parseList.clear();
        closure.clear();
        roots.clear();
        extensions.clear();
    }

    public synchronized ClassExt typeExt(Class<?> type) {
        ClassExt ext = extensions.get(type);
        if (ext == null) {
            ext = new ClassExt();
            extensions.put(type, ext);
        }
        return ext;
    }

    public int parseAll(Collection<Class<?>> classes) {
        int n = 0;
        for (Class<?> c : classes)
            n += parseClass(c);
        return n;
    }

    public int parseClass(Class<?> clazz) {
        parseList.add(clazz);
        return _addClass(clazz);
    }

    /**
     * Analyze the given class and all its parents, interfaces, annotations.
     *
     * @retu Modification counter.
     */
    int _addClass(Class<?> clazz) {
        if (! closure.add(clazz))
            return 0;

        int counter = 0;

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            counter += _addSuperclass(clazz, superclass);
        } else
            roots.add(clazz);

        for (Class<?> iface : clazz.getInterfaces())
            counter += _addInterface(clazz, iface);

        // clazz.getDeclaredAnnotations()
        for (Annotation annotation : clazz.getAnnotations())
            counter += _addAnnotation(clazz, annotation);

        return counter;
    }

    int _addSuperclass(Class<?> clazz, Class<?> superclass) {
        ClassExt superExt = typeExt(superclass);
        int counter = 0;
        if (superExt.addSubclass(clazz))
            counter++;

        counter += _addClass(superclass);
        return counter;
    }

    int _addInterface(Class<?> clazz, Class<?> iface) {
        int counter = 0;
        if (typeExt(iface).addImpl(clazz))
            counter++;

        for (Class<?> superInterface : iface.getInterfaces())
            counter += _addInterface(clazz, superInterface);

        counter += _addClass(iface); // iface is also parsed in full-scan.
        return counter;
    }

    int _addAnnotation(Class<?> clazz, Annotation a) {
        int counter = 0;
        if (typeExt(a.annotationType()).addAnnotated(clazz))
            counter++;

        // option:
        // @A @interface B, and @B class C, then @A class C.

        // annotationType is also analyzed in full-scan.
        // counter += parseClass(annotationType);
        return counter;
    }

    public int size() {
        return parseList.size();
    }

    public int classCount() {
        return closure.size();
    }

    @Override
    public String toString() {
        return String.format("parse %d, closure %d, roots %d, ext %d", //
                parseList.size(), closure.size(), roots.size(), extensions.size());
    }

    public int subclassTotal() {
        int total = 0;
        for (ClassExt ext : extensions.values())
            total += ext.subclasses.size();
        return total;
    }

    public Set<Class<?>> getDerivations(Class<?> base, boolean subclassing, boolean implementing, boolean annotating) {
        Set<Class<?>> derivations = new HashSet<Class<?>>();
        findDerivations(derivations, base, subclassing, implementing, annotating);
        return derivations;
    }

    void findDerivations(Set<Class<?>> results, Class<?> base, boolean subclassing, boolean implementing,
            boolean annotating) {
        ClassExt ext = typeExt(base);

        if (subclassing) {
            // assert !base.isAnnotation();
            for (Class<?> subclass : ext.subclasses) {
                if (results.add(subclass))
                    findDerivations(results, subclass, subclassing, implementing, annotating);
            }
        }

        if (implementing) {
            // assert base.isInterface();
            for (Class<?> impl : ext.implementations) {
                if (results.add(impl))
                    findDerivations(results, impl, subclassing, implementing, annotating);
            }
        }

        if (annotating) {
            Set<Class<?>> annotatedSet = ext.annotatedClasses;
            if (annotatedSet == null)
                return;

            if (! base.isAnnotation() && ! Collections.isEmpty(annotatedSet))
                throw new IllegalUsageException(String.format(//
                        "A non-annotation %s is used to annotate on: %s", base, annotatedSet));

            boolean inheritedAnnotation = base.isAnnotationPresent(Inherited.class);
            if (! inheritedAnnotation) {
                subclassing = false;
                implementing = false;
            }

            boolean implicitInherited = base.isAnnotationPresent(Implicit.class);
            if (! implicitInherited)
                annotating = false;

            for (Class<?> item : annotatedSet)
                if (results.add(item)) {
                    if (! item.isAnnotation())
                        findDerivations(results, item, subclassing, implementing, annotating);
                }
        }
    }

    protected Collection<Class<?>> listFilteredDerivations(Class<?> base) {
        List<Class<?>> list = new ArrayList<Class<?>>();

        Set<Class<?>> derivations = getDerivations(base, true, true, true);
        for (Class<?> derivation : derivations) {
            if (derivation.isAnonymousClass())
                continue;

            /*
             * Defined inside a code block, only visible inside that code block
             */
            if (derivation.isLocalClass())
                continue;

            if (derivation.isMemberClass())
                /*
                 * A class defined as non-static inside another class. Instances are dependent on an
                 * instance of the enclosing class.
                 */
                if (! Modifier.isStatic(derivation.getModifiers()))
                    continue;

            // ExcludedFromIndex a = derivation.getAnnotation(ExcludedFromIndex.class);
            if (derivation.isAnnotationPresent(ExcludedFromIndex.class))
                continue;
            if (derivation.isAnnotationPresent(ExcludedFromIndex.Inherited.class))
                continue;

            if (derivation.isAnnotationPresent(IndexedTypeLoader.class))
                continue;

            list.add(derivation);
        }
        return list;
    }

    public void dump() {
        for (Class<?> root : roots)
            dump("^", root, "", true, true, true);
    }

    public void dump(Class<?> root) {
        dump(root, true, true, true);
    }

    public void dump(Class<?> root, boolean subclassing, boolean implementing, boolean annotating) {
        dump("^", root, "", subclassing, implementing, annotating);
    }

    void dump(String type, Class<?> clazz, String prefix, boolean subclassing, boolean implementing,
            boolean annotating) {
        new Dumper(subclassing, implementing, annotating).dump(type, prefix, clazz);
    }

    class Dumper {

        boolean subclassing;
        boolean implementing;
        boolean annotating;

        IdentityObjectSet once = new IdentityObjectSet();
        PrintStream out = System.out;

        public Dumper(boolean subclassing, boolean implementing, boolean annotating) {
            this.subclassing = subclassing;
            this.implementing = implementing;
            this.annotating = annotating;
        }

        void dump(String type, String prefix, Class<?> clazz) {
            boolean dumped = ! once.add(clazz);

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

            ClassExt ext = typeExt(clazz);

            if (subclassing) {
                for (Class<?> subclass : ext.subclasses)
                    dump("<-- ", prefix, subclass);
            }

            if (implementing) {
                for (Class<?> impl : ext.implementations)
                    dump("o-- ", prefix, impl);
            }

            if (annotating) {
                for (Class<?> annotatedClass : ext.annotatedClasses)
                    dump("@-- ", prefix, annotatedClass);
            }
        }

    }

    public void dumpStat() {
        for (Entry<Class<?>, ClassExt> entry : extensions.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().subclasses.size() + " subclasses");
        }
    }

}
