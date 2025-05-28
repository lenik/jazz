package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeStrategy {

    @NotNull
    <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> makeRules(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException;

    <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T> makeDefaultRule(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException;

    default boolean canMake(@NotNull IKeyData<?, ?> target, @NotNull IDataBinding binding)
            throws CompileException {
        return makeDefaultRule(target, binding) != null;
    }

}
