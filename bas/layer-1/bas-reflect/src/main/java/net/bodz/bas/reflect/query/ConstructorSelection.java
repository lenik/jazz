package net.bodz.bas.reflect.query;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;

import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.ImmediateIterableX;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.collection.util.IterableToList;

public abstract class ConstructorSelection
        implements ImmediateIterableX<Constructor<?>, RuntimeException> {

    protected int modifierMask;
    protected int modifierTest;

    protected IParametersPredicate parametersPredicate;

    static final int accessModifierMask = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    public ConstructorSelection withAccess(boolean includePublic, boolean includeProtected, boolean includePrivate) {
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

    public ConstructorSelection staticOnly() {
        modifierMask |= Modifier.STATIC;
        modifierTest |= Modifier.STATIC;
        return this;
    }

    public ConstructorSelection finalOnly() {
        modifierMask |= Modifier.FINAL;
        modifierTest |= Modifier.FINAL;
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */

    public ConstructorSelection withParameters(Class<?>... parameters) {
        parametersPredicate = new PrefixParameters(parameters, parametersPredicate);
        return this;
    }

    public ConstructorSelection withMinParameters(Class<?>... parameters) {
        parametersPredicate = new MinPrefixParameters(parameters, parametersPredicate);
        return this;
    }

    public ConstructorSelection withMaxParameters(Class<?>... parameters) {
        parametersPredicate = new MinPrefixParameters(parameters, parametersPredicate);
        return this;
    }

    @Override
    public IteratorX<Constructor<?>, RuntimeException> iterator() {
        return new ImmIterIterator<Constructor<?>, RuntimeException>(this, true);
    }

    public Constructor<?>[] toArray() {
        return toList().toArray(new Constructor<?>[0]);
    }

    public List<? extends Constructor<?>> toList() {
        return IterableToList.toList(this);
    }

    public boolean exists() {
        return iterator(true).next() != null;
    }

}
