package net.bodz.bas.typer.std;

import java.util.Set;

/**
 * Pre-defined instances provider.
 */
public interface IInstanceStore<T>
        extends IStdTyper {

    int typerIndex = 0xa401a6d5; // IInstanceStore

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
