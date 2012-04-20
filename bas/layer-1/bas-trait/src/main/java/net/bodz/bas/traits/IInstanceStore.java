package net.bodz.bas.traits;

import java.util.Set;

/**
 * Pre-defined instances provider.
 */
public interface IInstanceStore<T> {

    int traitIndex = 1154332547; // IInstanceStore

    /**
     * 
     * @return Unmodifiable {@link Set} of instance names, empty if no instance available.
     */
    Set<String> getInstanceNames();

    /**
     * @return <code>null</code> If specified instance name doesn't exist.
     */
    T getInstance(String name);

}
