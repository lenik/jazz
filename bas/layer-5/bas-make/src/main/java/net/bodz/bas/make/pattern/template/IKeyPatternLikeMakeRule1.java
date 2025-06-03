package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.IMakeable1;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public interface IKeyPatternLikeMakeRule1<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKey<?, ?>, //
        Us extends IParameterizedKey<Param, UK>, UK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends IKeyPatternLikeMakeRule<Tp, Param, K, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    Us getInput1();

    @Override
    default BoundRule<T, K, TT> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();
        @SuppressWarnings("unchecked")
        Us input1s = (Us) getInputs()[0];

        Param param = pattern.match(target.getKey());
        if (param == null)
            return null;

        UK input1Key = input1s.getKey(param);
        if (input1Key == null)
            return null;

        @SuppressWarnings("unchecked")
        U input1 = (U) binding.getData(input1Key);
        if (input1 == null)
            return null;

        IMakeable1<TT, UT> fn = compile(target, input1);
        if (fn == null)
            return null;

        SimpleMakeRule1<T, K, TT, U, UK, UT> rule = SimpleMakeRule1.<T, K, TT, U, UK, UT>builder()//
                .priority(this.getPriority())//
                .input(input1)//
                .fn(fn).build();

        BoundRule<T, K, TT> instance = new BoundRule<>(rule, target, input1);
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
