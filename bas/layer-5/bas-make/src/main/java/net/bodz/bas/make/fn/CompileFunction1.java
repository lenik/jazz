package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction1<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT> //
        extends CompileFunction<T> {

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs) {
        return (t, iv) -> {
            @SuppressWarnings("unchecked")
            U input1 = (U) iv[0];

            IMakeable1<TT, UT> fn = compile(target, input1);

            TT tVal = fn.make(input1.getData());

            t.setData(tVal);
        };
    }

    IMakeable1<TT, UT> compile(@NotNull T target, U input1);

}
