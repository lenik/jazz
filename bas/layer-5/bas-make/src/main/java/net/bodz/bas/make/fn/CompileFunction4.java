package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction4<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT> //
        extends CompileFunction<T> {

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs) {
        return (t, iv) -> {
            @SuppressWarnings("unchecked")
            U input1 = (U) iv[0];

            @SuppressWarnings("unchecked")
            V input2 = (V) iv[1];

            @SuppressWarnings("unchecked")
            W input3 = (W) iv[2];

            @SuppressWarnings("unchecked")
            X input4 = (X) iv[3];

            IMakeable4<TT, UT, VT, WT, XT> fn = compile(target, input1, input2, input3, input4);

            TT tVal = fn.make(input1.getData(), input2.getData(), input3.getData(), input4.getData());

            t.setData(tVal);
        };
    }

    IMakeable4<TT, UT, VT, WT, XT> compile(@NotNull T target, U input1, V input2, W input3, X input4);

}
