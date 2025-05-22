package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction2<T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT> {

    MakeFunction2<TT, UT, VT> compile(@NotNull T target, U input1, V input2);

}
