package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.IMakeable0;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule0;
import net.bodz.bas.meta.decl.NotNull;

public interface ITargetPatternLikeMakeRule0<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
        Keys extends IParameterizedTarget<?, ?, ?, ?>, //
        T extends IKeyData<TK, TT>, TT> //
        extends ITargetPatternLikeMakeRule<Tp, Param, TK, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    @Override
    default BoundRule<T, TK, TT> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();

        Param param = pattern.match(target);
        if (param == null)
            return null;

        IMakeable0<TT> fn = compile(target);
        if (fn == null)
            return null;

        SimpleMakeRule0<T, TK, TT> rule = SimpleMakeRule0.<T, T, TK, TT>builder()//
                .priority(this.getPriority())//
                .input()//
                .fn(fn).build();

        BoundRule<T, TK, TT> instance = new BoundRule<>(rule, target);
        return instance;
    }

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException {
        IMakeable0<TT> fn = compile(target);
        return (t, iv) -> {
            TT tData = fn.make();
            t.setData(tData);
        };
    }

    IMakeable0<TT> compile(@NotNull T target)
            throws CompileException;

}
