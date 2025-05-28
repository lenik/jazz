package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.IMakeable0;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule0;
import net.bodz.bas.meta.decl.NotNull;

public interface IKeyPatternLikeMakeRule0<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        T extends IKeyData<K, TT>, TT> //
        extends IKeyPatternLikeMakeRule<Tp, Param, K, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    @Override
    default BoundRule<T> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();

        Param param = pattern.match(target.getKey());
        if (param == null)
            return null;

        IMakeable0<TT> fn = compile(target);
        if (fn == null)
            return null;

        SimpleMakeRule0<T, K, TT> rule = SimpleMakeRule0.<T, K, TT>builder()//
                .priority(this.getPriority())//
                .input()
                .fn(fn).build();

        BoundRule<T> instance = new BoundRule<>(rule, target);
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
