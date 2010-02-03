package net.bodz.bas.reflect.query;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.ImmediateIterableX;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.collection.util.IterableToList;
import net.bodz.bas.reflect.MethodSignature;

/**
 * @see ReflectQuery#selectMethods(Class)
 * @see ReflectQuery#selectDeclaredMethods(Class)
 * @test {@link MethodSelectionTest}
 */
public abstract class MethodSelection
        implements ImmediateIterableX<Method, RuntimeException> {

    protected int modifierMask;
    protected int modifierTest;

    protected INamePredicate namePredicate;
    protected IParametersPredicate parametersPredicate;
    protected ITypePredicate returnTypePredicate;

    static final int accessModifierMask = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    public MethodSelection withAccess(boolean includePublic, boolean includeProtected, boolean includePrivate) {
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

    public MethodSelection staticOnly() {
        modifierMask |= Modifier.STATIC;
        modifierTest |= Modifier.STATIC;
        return this;
    }

    public MethodSelection finalOnly() {
        modifierMask |= Modifier.FINAL;
        modifierTest |= Modifier.FINAL;
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public MethodSelection withName(String name) {
        namePredicate = new EqualsName(name, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public MethodSelection startsWithName(String namePrefix) {
        namePredicate = new StartsWithName(namePrefix, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>nameSuffix</code> is <code>null</code>.
     */
    public MethodSelection endsWithName(String nameSuffix) {
        namePredicate = new EndsWithName(nameSuffix, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>method</code> is <code>null</code>.
     */
    public MethodSelection overrideOf(Method method) {
        MethodSelection result = withName(method.getName());
        result = result.withParameters(method.getParameterTypes());
        return result;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public MethodSelection withParameters(Class<?>... parameters) {
        parametersPredicate = new EqualsParameters(parameters, false, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public MethodSelection withMinParameters(Class<?>... parameters) {
        parametersPredicate = new MinParameters(parameters, false, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public MethodSelection withMaxParameters(Class<?>... parameters) {
        parametersPredicate = new MinParameters(parameters, false, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public MethodSelection prefixWithParameters(Class<?>... parameters) {
        parametersPredicate = new EqualsParameters(parameters, true, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public MethodSelection prefixWithMinParameters(Class<?>... parameters) {
        parametersPredicate = new MinParameters(parameters, true, parametersPredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public MethodSelection prefixWithMaxParameters(Class<?>... parameters) {
        parametersPredicate = new MinParameters(parameters, true, parametersPredicate);
        return this;
    }

    public MethodSelection of(MethodSignature minSignature) {
        MethodSelection result = this;
        String name = minSignature.getMethodName();
        if (name != null)
            result = result.withName(name);
        Class<?>[] params = minSignature.getParameterTypes();
        assert params != null;
        result = result.withMinParameters(params);
        return result;
    }

    public MethodSelection superOf(MethodSignature minSignature) {
        MethodSelection result = this;
        String name = minSignature.getMethodName();
        if (name != null)
            result = result.withName(name);
        Class<?>[] params = minSignature.getParameterTypes();
        assert params != null;
        result = result.withMaxParameters(params);
        return result;
    }

    public MethodSelection returns(Class<?> minReturnType) {
        returnTypePredicate = new MinType(minReturnType, returnTypePredicate);
        return this;
    }

    public MethodSelection returnsSuperOf(Class<?> maxReturnType) {
        returnTypePredicate = new MaxType(maxReturnType, returnTypePredicate);
        return this;
    }

    @Override
    public IteratorX<Method, RuntimeException> iterator() {
        return new ImmIterIterator<Method, RuntimeException>(this, true);
    }

    public Method[] toArray() {
        return toList().toArray(new Method[0]);
    }

    public List<? extends Method> toList() {
        return IterableToList.toList(this);
    }

    public boolean exists() {
        return iterator(true).next() != null;
    }

}
