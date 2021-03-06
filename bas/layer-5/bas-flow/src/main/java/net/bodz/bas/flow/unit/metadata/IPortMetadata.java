package net.bodz.bas.flow.unit.metadata;

import java.util.Collection;

import net.bodz.bas.meta.stereo.IMetadata;

public interface IPortMetadata
        extends IMetadata {

    /**
     * @return display name
     */
    String getName();

    boolean isSupportedType(Class<?> type);

    /**
     * @return all supported types
     */
    Collection<Class<?>> getSupportedTypes();

    /**
     * Type with low order value is preferred.
     * 
     * no value defined for unsupported types, if a type is unsupported, you may want to return
     * {@link Integer#MAX_VALUE}.
     * 
     * @return a rank value indicating the preference of a given type.
     */
    int getTypeOrder(Class<?> type);

}
