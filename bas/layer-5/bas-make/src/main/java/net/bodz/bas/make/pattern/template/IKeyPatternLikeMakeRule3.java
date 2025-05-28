package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.IMakeable3;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule3;
import net.bodz.bas.meta.decl.NotNull;

public interface IKeyPatternLikeMakeRule3<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        Ws extends IParameterizedKeys<Param, WK>, WK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT> //
        extends IKeyPatternLikeMakeRule<Tp, Param, K, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    Us getInput1();

    Vs getInput2();

    Ws getInput3();

    @Override
    default BoundRule<T> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();
        @SuppressWarnings("unchecked")
        Us input1s = (Us) getInputs()[0];
        @SuppressWarnings("unchecked")
        Vs input2s = (Vs) getInputs()[1];
        @SuppressWarnings("unchecked")
        Ws input3s = (Ws) getInputs()[2];

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

        VK input2Key = input2s.getKey(param);
        if (input2Key == null)
            return null;

        @SuppressWarnings("unchecked")
        V input2 = (V) binding.getData(input2Key);
        if (input2 == null)
            return null;

        WK input3Key = input3s.getKey(param);
        if (input3Key == null)
            return null;

        @SuppressWarnings("unchecked")
        W input3 = (W) binding.getData(input3Key);
        if (input3 == null)
            return null;

        IMakeable3<TT, UT, VT, WT> fn = compile(target, input1, input2, input3);
        if (fn == null)
            return null;

        SimpleMakeRule3<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT> rule = SimpleMakeRule3.<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder()//
                .priority(this.getPriority())//
                .input(input1, input2, input3)
                .fn(fn).build();

        BoundRule<T> instance = new BoundRule<>(rule, target, input1, input2, input3);
        return instance;
    }

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        @SuppressWarnings("unchecked")
        V input2 = (V) inputs[1];
        @SuppressWarnings("unchecked")
        W input3 = (W) inputs[2];
        IMakeable3<TT, UT, VT, WT> fn = compile(target, input1, input2, input3);
        return (t, iv) -> {
            TT tData = fn.make(input1.getData(), input2.getData(), input3.getData());
            t.setData(tData);
        };
    }

    IMakeable3<TT, UT, VT, WT> compile(@NotNull T target, U input1, V input2, W input3)
            throws CompileException;

}
