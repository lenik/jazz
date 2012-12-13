package net.bodz.bas.c.reflect.query;

import java.lang.reflect.Modifier;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.query.predicate.*;
import net.bodz.bas.t.iterator.immed.AbstractMitable;

public abstract class _TypeVectorSelection<T, $ extends _TypeVectorSelection<T, $>>
        extends AbstractMitable<T> {

    protected int modifierMask;
    protected int modifierTest;

    protected IStringPredicate namePredicate;
    protected ITypeVectorPredicate parametersPredicate;
    protected ITypePredicate returnTypePredicate;

    static final int accessModifierMask = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    protected final $ self() {
        @SuppressWarnings("unchecked")
        $ _this = ($) this;
        return _this;
    }

    public $ withAccess(boolean includePublic, boolean includeProtected, boolean includePrivate) {
        modifierMask |= accessModifierMask;
        modifierTest &= ~accessModifierMask;
        if (includePublic)
            modifierTest |= Modifier.PUBLIC;
        if (includeProtected)
            modifierTest |= Modifier.PROTECTED;
        if (includePrivate)
            modifierTest |= Modifier.PRIVATE;
        return self();
    }

    public $ staticOnly() {
        modifierMask |= Modifier.STATIC;
        modifierTest |= Modifier.STATIC;
        return self();
    }

    public $ finalOnly() {
        modifierMask |= Modifier.FINAL;
        modifierTest |= Modifier.FINAL;
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public $ nameEquals(String name) {
        namePredicate = new StringEquals(name, namePredicate);
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public $ nameStartsWith(String namePrefix) {
        namePredicate = new StringStartsWith(namePrefix, namePredicate);
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>nameSuffix</code> is <code>null</code>.
     */
    public $ nameEndsWith(String nameSuffix) {
        namePredicate = new StringEndsWith(nameSuffix, namePredicate);
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public $ parametersEquals(Class<?>... parameters) {
        parametersPredicate = new TypeVectorEquals(parameters, false, parametersPredicate);
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public $ parametersDerivedFrom(Class<?>... parameters) {
        parametersPredicate = new TypeVectorDerivedFrom(parameters, false, parametersPredicate);
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public $ parametersAncestorOf(Class<?>... parameters) {
        parametersPredicate = new TypeVectorAncestorOf(parameters, false, parametersPredicate);
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public $ parametersStartsWith(Class<?>... parameters) {
        parametersPredicate = new TypeVectorEquals(parameters, true, parametersPredicate);
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public $ parametersStartsWithDerivedFrom(Class<?>... parameters) {
        parametersPredicate = new TypeVectorDerivedFrom(parameters, true, parametersPredicate);
        return self();
    }

    /**
     * @throws NullPointerException
     *             If <code>parameters</code> is <code>null</code>.
     */
    public $ parametersStartsWithAncestorOf(Class<?>... parameters) {
        parametersPredicate = new TypeVectorAncestorOf(parameters, true, parametersPredicate);
        return self();
    }

    public $ of(MethodSignature ancestorSignature) {
        $ result = self();
        String name = ancestorSignature.getName();
        if (name != null)
            result = result.nameEquals(name);
        Class<?>[] params = ancestorSignature.getParameterTypes();
        assert params != null;
        result = result.parametersDerivedFrom(params);
        return result;
    }

    public $ ancestorOf(MethodSignature endSignature) {
        $ result = self();
        String name = endSignature.getName();
        if (name != null)
            result = result.nameEquals(name);
        Class<?>[] params = endSignature.getParameterTypes();
        assert params != null;
        result = result.parametersAncestorOf(params);
        return result;
    }

    public $ returns(Class<?> ancestorType) {
        returnTypePredicate = new TypeDerivedFrom(ancestorType, returnTypePredicate);
        return self();
    }

    public $ returnsAncestorOf(Class<?> endType) {
        returnTypePredicate = new TypeAncestorOf(endType, returnTypePredicate);
        return self();
    }

    public boolean exists() {
        return iterator(true)._next() != null;
    }

}
