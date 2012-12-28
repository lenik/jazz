package net.bodz.bas.potato.element;

import java.util.Set;

public interface IPropertyMap {

    int getPropertyCount();

    Iterable<IProperty> getProperties();

    Set<String> getPropertyNames();

    boolean containsProperty(String propertyName);

    /**
     * Find the matching property.
     * <p>
     * There should be only one matching property at most.
     * 
     * @param propertyKey
     *            Non-<code>null</code> property key to match.
     * @return The matched property if any, or <code>null</code> if none matched.
     */
    IProperty getProperty(String propertyName);

}
