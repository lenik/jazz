package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRule0<T extends IKeyData<TK, TT>, TK, TT>
        extends IMakeRule<T>
        // , IMakeable0<TT>
{

    @NotNull
    @Override
    default IKeyData<?, ?>[] getInputs() {
        return new IKeyData[] {};
    }

    @Override
    default void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException {
        make(target);
    }

    default void make(@NotNull T target)
            throws MakeException {
        TT targetData = make();
        target.setData(targetData);
    }

    TT make()
            throws MakeException;

}
