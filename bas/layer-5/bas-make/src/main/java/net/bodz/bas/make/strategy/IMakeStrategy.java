package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.make.IMakeSession;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeStrategy {

    @NotNull
    <T extends IKeyData<?, ?>> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IMakeSession session)
            throws CompileException;

}
