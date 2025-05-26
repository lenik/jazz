package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction0<T extends IKeyData<TK, TT>, TK, TT> //
        extends CompileFunction<T> {

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs) {
        return (t, iv) -> {
            IMakeable0<TT> fn = compile(target);

            TT tVal = fn.make();

            t.setData(tVal);
        };
    }

    IMakeable0<TT> compile(@NotNull T target);

}
