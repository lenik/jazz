package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.IMakeable1;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public interface ITargetPatternLikeMakeRule1<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
        Keys extends IParameterizedTarget<?, ?, ?, ?>, //
        Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends ITargetPatternLikeMakeRule<Tp, Param, TK, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    Us getInput1();

    @Override
    default BoundRule<T, TK, TT> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();
        @SuppressWarnings("unchecked")
        Us input1s = (Us) getInputs()[0];

        Param param = pattern.match(target);
        if (param == null)
            return null;

        U input1 = input1s.getTarget(param, binding);
        if (input1 == null)
            return null;

        IMakeable1<TT, UT> fn = compile(target, input1);
        if (fn == null)
            return null;

        SimpleMakeRule1<T, TK, TT, U, UK, UT> rule = SimpleMakeRule1.<T, T, TK, TT, U, UK, UT>builder()//
                .priority(this.getPriority())//
                .input(input1)//
                .fn(fn).build();

        BoundRule<T, TK, TT> instance = new BoundRule<>(rule, target, input1);
        return instance;
    }

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        IMakeable1<TT, UT> fn = compile(target, input1);
        return (t, iv) -> {
            TT tData = fn.make(input1.getData());
            t.setData(tData);
        };
    }

    IMakeable1<TT, UT> compile(@NotNull T target, U input1)
            throws CompileException;

}
