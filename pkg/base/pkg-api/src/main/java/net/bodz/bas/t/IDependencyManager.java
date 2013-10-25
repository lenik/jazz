package net.bodz.bas.t;

import java.util.Collection;

public interface IDependencyManager<T extends IDependencyAware<?>> {

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    void updateDependencies();

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    Collection<? extends T> getDependencies(T obj);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    Iterable<? extends T> dependencyClosure(T obj);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    Collection<? extends T> getDependents(T obj);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    Iterable<? extends T> dependentClosure(T obj);

}
