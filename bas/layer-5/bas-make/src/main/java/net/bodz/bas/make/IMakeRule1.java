package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRule1<T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT>
        extends IMakeRule<T> {

    @NotNull
    @Override
    default IDataEntry<?, ?>[] getInputs() {
        return new IDataEntry[] { getInput1() };
    }

    U getInput1();

    @Override
    default void make(@NotNull T target, @NotNull IDataEntry<?, ?>... inputs)
            throws MakeException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        make(target, input1);
    }

    default void make(@NotNull T target, @NotNull U in)
            throws MakeException {
        TT targetData = make(in.getData());
        target.setData(targetData);
    }

    TT make(UT input)
            throws MakeException;

}
