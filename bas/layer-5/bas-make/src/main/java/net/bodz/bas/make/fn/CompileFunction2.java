package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction2<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT> //
        extends CompileFunction<T> {

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs) {
        return (t, iv) -> {
            @SuppressWarnings("unchecked")
            U input1 = (U) iv[0];

            @SuppressWarnings("unchecked")
            V input2 = (V) iv[1];

            IMakeable2<TT, UT, VT> fn = compile(target, input1, input2);

            TT tVal = fn.make(input1.getData(), input2.getData());

            t.setData(tVal);
        };
    }

    IMakeable2<TT, UT, VT> compile(@NotNull T target, U input1, V input2);

}
