package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction1<T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT> {

    MakeFunction1<TT, UT> compile(@NotNull T target, U input1);

}
