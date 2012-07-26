package net.bodz.bas.potato.traits;

import java.util.Collection;

public interface IPropertyMap {

    int size();

    Collection<IProperty> getProperties();

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
