package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRule1<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT>
        extends IMakeRule<T, TK, TT>
{

    @Override
    default IDataTypedKey<?, ?>[] getInputs() {
        return new IDataTypedKey<?, ?>[] { getInput1() };
    }

    IDataTypedKey<UK, UT> getInput1();

    @Override
    default void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];

        make(target, input1);
    }

    default void make(@NotNull T target, @NotNull U input1)
            throws MakeException {
        TT targetData = make(input1.getData());
        target.setData(targetData);
    }

    TT make(UT input1)
            throws MakeException;

}
