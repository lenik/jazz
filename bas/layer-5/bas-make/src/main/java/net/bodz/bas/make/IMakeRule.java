package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface IMakeRule<T extends IDataEntry<?, ?>>
        extends IPriority {

//    @NotNull
//    Class<T> getType();

    @NotNull
    IDataEntry<?, ?>[] getInputs();

    default boolean canMake() {
        return true;
    }

    void make(@NotNull T target, @NotNull IDataEntry<?, ?>... inputs)
            throws MakeException;

    default int getEstimatedCost(@NotNull T target, @NotNull IDataEntry<?, ?>... inputs) {
        return 1;
    }

}
