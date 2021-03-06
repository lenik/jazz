package net.bodz.bas.c.reflect.query;

import java.lang.reflect.Method;
import java.util.List;

import net.bodz.bas.t.iterator.Iterables;

/**
 * @see ReflectQuery#selectMethods(Class)
 * @see ReflectQuery#selectDeclaredMethods(Class)
 */
public abstract class MethodSelection
        extends _TypeVectorSelection<Method, MethodSelection> {

    /**
     * @throws NullPointerException
     *             If <code>method</code> is <code>null</code>.
     */
    public MethodSelection overrideOf(Method method) {
        MethodSelection result = nameEquals(method.getName());
        result = result.parametersEquals(method.getParameterTypes());
        return this;
    }

    public Method[] toArray() {
        return toList().toArray(new Method[0]);
    }

    public List<? extends Method> toList() {
        return Iterables.toList(this);
    }

}
