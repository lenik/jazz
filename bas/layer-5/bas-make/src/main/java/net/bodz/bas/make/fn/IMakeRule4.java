package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRule4<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT>
        extends IMakeRule<T>
{

    @Override
    default IDataTypedKey<?, ?>[] getInputs() {
        return new IDataTypedKey<?, ?>[] { getInput1(), getInput2(), getInput3(), getInput4() };
    }

    IDataTypedKey<UK, UT> getInput1();

    IDataTypedKey<VK, VT> getInput2();

    IDataTypedKey<WK, WT> getInput3();

    IDataTypedKey<XK, XT> getInput4();

    @Override
    default void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        @SuppressWarnings("unchecked")
        V input2 = (V) inputs[1];
        @SuppressWarnings("unchecked")
        W input3 = (W) inputs[2];
        @SuppressWarnings("unchecked")
        X input4 = (X) inputs[3];

        make(target, input1, input2, input3, input4);
    }

    default void make(@NotNull T target, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4)
            throws MakeException {
        TT targetData = make(input1.getData(), input2.getData(), input3.getData(), input4.getData());
        target.setData(targetData);
    }

    TT make(UT input1, VT input2, WT input3, XT input4)
            throws MakeException;

}
