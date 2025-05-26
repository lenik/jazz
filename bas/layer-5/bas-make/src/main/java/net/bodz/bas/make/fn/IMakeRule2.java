package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRule2<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT>
        extends IMakeRule<T>
{

    @Override
    default IDataTypedKey<?, ?>[] getInputs() {
        return new IDataTypedKey<?, ?>[] { getInput1(), getInput2() };
    }

    IDataTypedKey<UK, UT> getInput1();

    IDataTypedKey<VK, VT> getInput2();

    @Override
    default void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        @SuppressWarnings("unchecked")
        V input2 = (V) inputs[1];

        make(target, input1, input2);
    }

    default void make(@NotNull T target, @NotNull U input1, @NotNull V input2)
            throws MakeException {
        TT targetData = make(input1.getData(), input2.getData());
        target.setData(targetData);
    }

    TT make(UT input1, VT input2)
            throws MakeException;

}
