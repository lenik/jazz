package net.bodz.bas.typeinfo;

import java.util.List;

public interface InstanceStore<T> {

    int getInstanceCount();

    /**
     * @return non-<code>null</code> instance.
     * @throws IndexOutOfBoundsException
     */
    T getInstance(int index);

    /**
     * 
     * @return Unmodifiable list of instance names, empty list if no instance name defined.
     */
    List<String> getInstanceNames();

    /**
     * @return <code>null</code> if instance with specified name doesn't exist.
     */
    T getInstance(String name);

}
