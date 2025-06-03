package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRule7<T extends IKeyData<TK, TT>, TK, TT, //
        U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7K, U7T>
        extends IMakeRule<T, TK, TT>
{

    @Override
    default IDataTypedKey<?, ?>[] getInputs() {
        return new IDataTypedKey<?, ?>[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5(), getInput6(), getInput7() };
    }

    IDataTypedKey<U1K, U1T> getInput1();

    IDataTypedKey<U2K, U2T> getInput2();

    IDataTypedKey<U3K, U3T> getInput3();

    IDataTypedKey<U4K, U4T> getInput4();

    IDataTypedKey<U5K, U5T> getInput5();

    IDataTypedKey<U6K, U6T> getInput6();

    IDataTypedKey<U7K, U7T> getInput7();

    @Override
    default void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException {
        @SuppressWarnings("unchecked")
        U1 input1 = (U1) inputs[0];
        @SuppressWarnings("unchecked")
        U2 input2 = (U2) inputs[1];
        @SuppressWarnings("unchecked")
        U3 input3 = (U3) inputs[2];
        @SuppressWarnings("unchecked")
        U4 input4 = (U4) inputs[3];
        @SuppressWarnings("unchecked")
        U5 input5 = (U5) inputs[4];
        @SuppressWarnings("unchecked")
        U6 input6 = (U6) inputs[5];
        @SuppressWarnings("unchecked")
        U7 input7 = (U7) inputs[6];

        make(target, input1, input2, input3, input4, input5, input6, input7);
    }

    default void make(@NotNull T target, @NotNull U1 input1, @NotNull U2 input2, @NotNull U3 input3, @NotNull U4 input4, @NotNull U5 input5, @NotNull U6 input6, @NotNull U7 input7)
            throws MakeException {
        TT targetData = make(input1.getData(), input2.getData(), input3.getData(), input4.getData(), input5.getData(), input6.getData(), input7.getData());
        target.setData(targetData);
    }

    TT make(U1T input1, U2T input2, U3T input3, U4T input4, U5T input5, U6T input6, U7T input7)
            throws MakeException;

}
