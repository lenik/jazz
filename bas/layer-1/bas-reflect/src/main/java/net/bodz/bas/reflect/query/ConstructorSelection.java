package net.bodz.bas.reflect.query;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;

import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.ImmediateIterableX;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.collection.util.IterableToList;
import net.bodz.bas.reflect.MethodSignature;

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
        parametersPredicate = new EqualsParameters(parameters, false, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public ConstructorSelection withMinParameters(Class<?>... parameters) {
        parametersPredicate = new MinParameters(parameters, false, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public ConstructorSelection withMaxParameters(Class<?>... parameters) {
        parametersPredicate = new MinParameters(parameters, false, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public ConstructorSelection prefixWithParameters(Class<?>... parameters) {
        parametersPredicate = new EqualsParameters(parameters, true, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public ConstructorSelection prefixWithMinParameters(Class<?>... parameters) {
        parametersPredicate = new MinParameters(parameters, true, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public ConstructorSelection prefixWithMaxParameters(Class<?>... parameters) {
        parametersPredicate = new MinParameters(parameters, true, parametersPredicate);
        return this;
    }

    public ConstructorSelection of(MethodSignature minSignature) {
        ConstructorSelection result = this;
        Class<?>[] params = minSignature.getParameterTypes();
        assert params != null;
        result = result.withMinParameters(params);
        return result;
    }

    public ConstructorSelection superOf(MethodSignature minSignature) {
        ConstructorSelection result = this;
        Class<?>[] params = minSignature.getParameterTypes();
        assert params != null;
        result = result.withMaxParameters(params);
        return result;
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
