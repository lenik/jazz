package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface IMakeRule<T extends IKeyData<?, ?>>
        extends IPriority {

    @NotNull
    IKeyData<?, ?>[] getInputs();

    default boolean canMake(@NotNull T target, IMakeSession session) {
        if (!session.canMake(target))
            return false;
        for (IKeyData<?, ?> input : getInputs()) {
            assert input != null;
            if (!session.canMake(input))
                return false;
        }
        return true;
    }

    void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException;

    default int getEstimatedCost(@NotNull T target, @NotNull IKeyData<?, ?>... inputs) {
        return 1;
    }

}
