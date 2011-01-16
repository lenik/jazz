package net.bodz.bas.potato.traits;

import java.util.Map;

public interface IPropertyMap
        extends Map<String, IProperty> {

    /**
     * Find the matching property.
     * <p>
     * There should be only one matching property at most.
     * 
     * @param propertyKey
     *            Non-<code>null</code> property key to match.
     * @return The matched property if any, or <code>null</code> if none matched.
     */
    IProperty getProperty(PropertyKey propertyKey);

}
