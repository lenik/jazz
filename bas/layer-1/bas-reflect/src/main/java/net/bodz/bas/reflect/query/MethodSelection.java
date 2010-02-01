package net.bodz.bas.reflect.query;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.ImmediateIterableX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.iterator.IteratorTargetException;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

public abstract class MethodSelection
        implements ImmediateIterableX<Method, RuntimeException> {

    protected INamePredicate namePredicate;
    protected IParametersPredicate parametersPredicate;

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
     *             If <code>parameters</code> is <code>null</code>.
     */

    public MethodSelection withParameters(Class<?>... parameters) {
        parametersPredicate = new PrefixParameters(parameters, parametersPredicate);
        return this;
    }

    public MethodSelection withMinParameters(Class<?>... parameters) {
        parametersPredicate = new MinPrefixParameters(parameters, parametersPredicate);
        return this;
    }

    public MethodSelection withMaxParameters(Class<?>... parameters) {
        parametersPredicate = new MinPrefixParameters(parameters, parametersPredicate);
        return this;
    }

    @Override
    public IteratorX<Method, RuntimeException> iterator()
            throws IteratorTargetException {
        return new ImmIterIterator<Method, RuntimeException>(this, true);
    }

    public Method[] toArray()
            throws ReflectiveOperationException {
        return toList().toArray(new Method[0]);
    }

    public List<Method> toList()
            throws ReflectiveOperationException {
        ImmediateIteratorX<? extends Method, ? extends RuntimeException> iterator = iterator(false);
        List<Method> list = new ArrayList<Method>();
        Method m;
        while ((m = iterator.next()) != null || !iterator.isEnded())
            list.add(m);
        return list;
    }

    public boolean exists() {
        return iterator(true).next() != null;
    }

}
