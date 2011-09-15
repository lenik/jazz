package net.bodz.bas.reflect.query;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.bodz.bas.util.iter.AbstractMitablex;
import net.bodz.bas.util.iter.AbstractMitorx;
import net.bodz.bas.util.iter.Iterables;
import net.bodz.bas.util.iter.Mitorx;

/**
 * @test {@link ClassSelectionTest}
 */
public class ClassSelection
        extends AbstractMitablex<Class<?>, RuntimeException> {

    private final Class<?> clazz;

    protected int modifierMask;
    protected int modifierTest;

    protected INamePredicate namePredicate;

    public ClassSelection(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    static final int accessModifierMask = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    public ClassSelection withAccess(boolean includePublic, boolean includeProtected, boolean includePrivate) {
        modifierMask |= accessModifierMask;
        modifierTest &= ~accessModifierMask;
        if (includePublic)
            modifierTest |= Modifier.PUBLIC;
        if (includeProtected)
            modifierTest |= Modifier.PROTECTED;
        if (includePrivate)
            modifierTest |= Modifier.PRIVATE;
        return this;
    }

    public ClassSelection staticOnly() {
        modifierMask |= Modifier.STATIC;
        modifierTest |= Modifier.STATIC;
        return this;
    }

    public ClassSelection finalOnly() {
        modifierMask |= Modifier.FINAL;
        modifierTest |= Modifier.FINAL;
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public ClassSelection withName(String name) {
        namePredicate = new EqualsName(name, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public ClassSelection startsWithName(String namePrefix) {
        namePredicate = new StartsWithName(namePrefix, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>nameSuffix</code> is <code>null</code>.
     */
    public ClassSelection endsWithName(String nameSuffix) {
        namePredicate = new EndsWithName(nameSuffix, namePredicate);
        return this;
    }

    public Class<?>[] toArray() {
        return toList().toArray(new Class<?>[0]);
    }

    public List<? extends Class<?>> toList() {
        return Iterables.toList(this);
    }

    public boolean exists() {
        return iterator(true)._next() != null;
    }

    protected Class<?> stopClass;

    protected boolean withInterfaces;
    protected boolean uniqueInterface;

    /**
     * @param stopClass
     *            <code>null</code> If not constrained.
     */
    public ClassSelection stopAt(Class<?> stopClass) {
        this.stopClass = stopClass;
        return this;
    }

    public ClassSelection withInterfaces() {
        this.withInterfaces = true;
        return this;
    }

    public ClassSelection unique() {
        uniqueInterface = true;
        return this;
    }

    class Iter
            extends AbstractMitorx<Class<?>, RuntimeException> {

        Class<?> currentClass;
        boolean currentClassIterated;
        LinkedList<Class<?>> interfaceQueue;
        Set<Class<?>> unique;

        public Iter() {
            currentClass = clazz;
            if (withInterfaces) {
                interfaceQueue = new LinkedList<Class<?>>();
                if (uniqueInterface)
                    unique = new HashSet<Class<?>>();
            }
        }

        @Override
        public Class<?> _next()
                throws RuntimeException {
            if (currentClass == null)
                return end();
            if (currentClassIterated) {
                if (withInterfaces)
                    while (!interfaceQueue.isEmpty()) {
                        Class<?> q = interfaceQueue.removeLast();
                        if (uniqueInterface)
                            if (unique.contains(q))
                                continue;
                            else
                                unique.add(q);
                        if (test(q))
                            return q;
                    }
                currentClass = currentClass.getSuperclass();
                currentClassIterated = false;
                return _next();
            } else {
                currentClassIterated = true;
                if (stopClass != null && currentClass.isAssignableFrom(stopClass))
                    return end();
                if (withInterfaces)
                    for (Class<?> iface : currentClass.getInterfaces())
                        interfaceQueue.add(iface);
                return test(currentClass) ? currentClass : _next();
            }
        }

    }

    boolean test(Class<?> clazz) {
        if (modifierTest != (modifierMask & clazz.getModifiers()))
            return false;
        if (namePredicate != null && !namePredicate.test(clazz.getName()))
            return false;
        return true;
    }

    @Override
    public Mitorx<? extends Class<?>, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
