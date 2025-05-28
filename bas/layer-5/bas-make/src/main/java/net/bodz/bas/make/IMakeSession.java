package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.plan.IMakeNode;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeSession
        extends IDataBinding {

    @NotNull
    IMakeRules getRules();

    default <T extends IKeyData<?, ?>> boolean canMake(@NotNull T target)
            throws CompileException {
        return getRules().canMake(target, this);
    }

    @NotNull
    default <T extends IKeyData<TK, TT>, TK, TT> List<IMakeRule<T>> makeRules(@NotNull T target)
            throws CompileException {
        return getRules().makeRules(target, this);
    }

    default <T extends IKeyData<TK, TT>, TK, TT> IMakeRule<T> makeDefaultRule(@NotNull T target)
            throws CompileException {
        return getRules().makeDefaultRule(target, this);
    }

    default IMakeNode makeGraph(@NotNull IKeyData<?, ?> target)
            throws CompileException {
        return makeGraph(target, false);
    }

    IMakeNode makeGraph(@NotNull IKeyData<?, ?> target, boolean reduce)
            throws CompileException;

    default IMakeNode makePlan(@NotNull IKeyData<?, ?> target)
            throws CompileException {
        IMakeNode start = makeGraph(target);
        if (start == null)
            return null;
        IMakeNode reduced = start.reduceToMinCost();
        return reduced;
    }

    <T extends IKeyData<TK, TT>, TK, TT> void make(T target)
            throws MakeException;

}
