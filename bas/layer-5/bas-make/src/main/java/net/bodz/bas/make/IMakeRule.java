package net.bodz.bas.make;

import net.bodz.bas.make.strategy.IMakeStrategy;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface IMakeRule<T extends IKeyData<TK, TT>, TK, TT>
        extends IPriority,
                ITargetType<T, TK, TT> {

    default IMakeStrategy getSource() {
        return null;
    }

    IDataTypedKey<?, ?>[] getInputs();

    default boolean canMake(IMakeSession session, @NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws CompileException {
        if (!session.canMake(target))
            return false;
        for (IKeyData<?, ?> input : inputs) {
            assert input != null;
            if (!session.canMake(input))
                return false;
        }
        return true;
    }

    void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException;

    default double getEstimatedCost(@NotNull T target, @NotNull IKeyData<?, ?>... inputs) {
        return 1;
    }

    default BoundRule<T, TK, TT> bind(T target, @NotNull IKeyData<?, ?>... inputs) {
        return new BoundRule<>(this, target, inputs);
    }

}
