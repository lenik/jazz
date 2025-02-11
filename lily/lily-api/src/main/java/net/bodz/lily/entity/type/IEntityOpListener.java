package net.bodz.lily.entity.type;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.lily.entity.manager.ForEntityType;

/**
 * @see ForEntityType
 */
@IndexedType
public interface IEntityOpListener<T>
        extends IPriority {

    Class<T> getValueType();

    default void onCreate(T o) {
    }

    default void onLoad(T o) {
    }

    default void validate(T o) {
    }

    default boolean beforeUpdate(T o, boolean updateExisting) {
        return false;
    }

    default boolean afterUpdate(T o, boolean updateExisting) {
        return false;
    }

    default boolean canDelete(T o) {
        return true;
    }

    /** @return true if something took effect */
    default boolean beforeDelete(T o) {
        return false;
    }

    /** @return true if something took effect */
    default boolean afterDelete(T o) {
        return false;
    }

}
