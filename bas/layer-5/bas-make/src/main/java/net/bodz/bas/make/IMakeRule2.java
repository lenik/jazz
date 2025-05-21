package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRule2<T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT, V extends IDataEntry<VK, VT>, VK, VT>
        extends IMakeRule<T> {

    @NotNull
    @Override
    default IDataEntry<?, ?>[] getInputs() {
        return new IDataEntry[] { getInput1(), getInput2() };
    }

    U getInput1();

    V getInput2();

    @Override
    default void make(@NotNull T target, @NotNull IDataEntry<?, ?>[] inputs)
            throws MakeException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        @SuppressWarnings("unchecked")
        V input2 = (V) inputs[1];
        make(target, input1, input2);
    }

    default void make(@NotNull T target, @NotNull U in1, @NotNull V in2)
            throws MakeException {
        TT targetData = make(in1.getData(), in2.getData());
        target.setData(targetData);
    }

    TT make(UT in1, VT in2)
            throws MakeException;

}
