package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface IMakeRule<T extends IKeyData<?, ?>>
        extends IPriority {

    @NotNull
    IKeyData<?, ?>[] getInputs();

    default boolean canMake(IMakeSession session, @NotNull T target) {
        if (!session.canMake(target))
            return false;
        for (IKeyData<?, ?> input : getInputs()) {
            assert input != null;
            if (!session.canMake(input))
                return false;
        }
        return true;
    }

    /**
     * @param inputs Exactly getInputs().
     */
    void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException;

    default double getEstimatedCost(@NotNull T target, @NotNull IKeyData<?, ?>... inputs) {
        return 1;
    }

}
