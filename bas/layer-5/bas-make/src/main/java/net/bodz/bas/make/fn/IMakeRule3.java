package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRule3<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT>
        extends IMakeRule<T>
        // , IMakeable3<TT, UT, VT, WT>
{

    @NotNull
    @Override
    default IKeyData<?, ?>[] getInputs() {
        return new IKeyData[] { getInput1(), getInput2(), getInput3() };
    }

    U getInput1();

    V getInput2();

    W getInput3();

    @Override
    default void make(@NotNull T target, @NotNull IKeyData<?, ?>[] inputs)
            throws MakeException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        @SuppressWarnings("unchecked")
        V input2 = (V) inputs[1];
        @SuppressWarnings("unchecked")
        W input3 = (W) inputs[2];
        make(target, input1, input2, input3);
    }

    default void make(@NotNull T target, @NotNull U input1, @NotNull V input2, @NotNull W input3)
            throws MakeException {
        TT targetData = make(input1.getData(), input2.getData(), input3.getData());
        target.setData(targetData);
    }

    TT make(UT input1, VT input2, WT input3)
            throws MakeException;

}
