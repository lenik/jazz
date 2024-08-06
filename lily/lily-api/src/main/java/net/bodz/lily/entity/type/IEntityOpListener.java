package net.bodz.lily.entity.type;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.lily.entity.manager.ForEntityType;

/**
 * @see ForEntityType
 */
@IndexedType
public interface IEntityOpListener<T>
        extends
            IPriority {

    Class<T> getValueType();

    default void onCreate(T o) {
    }

    default void onLoad(T o) {
    }

    default void validate(T o) {
    }

    default void beforeUpdate(T o, boolean updateExisting) {
    }

    default void afterUpdate(T o, boolean updateExisting) {
    }

    default boolean canDelete(T o) {
        return true;
    }

    default void beforeDelete(T o) {
    }

    default void afterDelete(T o) {
    }

}
