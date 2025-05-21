package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction2<T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT, V extends IDataEntry<VK, VT>, VK, VT> {

    MakeFunction2<TT, UT, VT> compile(@NotNull T target, U input1, V input2);

}
