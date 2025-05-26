package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction7<T extends IKeyData<TK, TT>, TK, TT, //
        U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
        extends CompileFunction<T> {

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs) {
        return (t, iv) -> {
            @SuppressWarnings("unchecked")
            U1 input1 = (U1) iv[0];

            @SuppressWarnings("unchecked")
            U2 input2 = (U2) iv[1];

            @SuppressWarnings("unchecked")
            U3 input3 = (U3) iv[2];

            @SuppressWarnings("unchecked")
            U4 input4 = (U4) iv[3];

            @SuppressWarnings("unchecked")
            U5 input5 = (U5) iv[4];

            @SuppressWarnings("unchecked")
            U6 input6 = (U6) iv[5];

            @SuppressWarnings("unchecked")
            U7 input7 = (U7) iv[6];

            IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn = compile(target, input1, input2, input3, input4, input5, input6, input7);

            TT tVal = fn.make(input1.getData(), input2.getData(), input3.getData(), input4.getData(), input5.getData(), input6.getData(), input7.getData());

            t.setData(tVal);
        };
    }

    IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> compile(@NotNull T target, U1 input1, U2 input2, U3 input3, U4 input4, U5 input5, U6 input6, U7 input7);

}
