package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.IMakeable4;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule4;
import net.bodz.bas.meta.decl.NotNull;

public interface IKeyPatternLikeMakeRule4<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKey<?, ?>, //
        Us extends IParameterizedKey<Param, UK>, UK, //
        Vs extends IParameterizedKey<Param, VK>, VK, //
        Ws extends IParameterizedKey<Param, WK>, WK, //
        Xs extends IParameterizedKey<Param, XK>, XK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT> //
        extends IKeyPatternLikeMakeRule<Tp, Param, K, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    Us getInput1();

    Vs getInput2();

    Ws getInput3();

    Xs getInput4();

    @Override
    default BoundRule<T, K, TT> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();
        @SuppressWarnings("unchecked")
        Us input1s = (Us) getInputs()[0];
        @SuppressWarnings("unchecked")
        Vs input2s = (Vs) getInputs()[1];
        @SuppressWarnings("unchecked")
        Ws input3s = (Ws) getInputs()[2];
        @SuppressWarnings("unchecked")
        Xs input4s = (Xs) getInputs()[3];

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

        XK input4Key = input4s.getKey(param);
        if (input4Key == null)
            return null;

        @SuppressWarnings("unchecked")
        X input4 = (X) binding.getData(input4Key);
        if (input4 == null)
            return null;

        IMakeable4<TT, UT, VT, WT, XT> fn = compile(target, input1, input2, input3, input4);
        if (fn == null)
            return null;

        SimpleMakeRule4<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> rule = SimpleMakeRule4.<T, T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>builder()//
                .priority(this.getPriority())//
                .input(input1, input2, input3, input4)//
                .fn(fn).build();

        BoundRule<T, K, TT> instance = new BoundRule<>(rule, target, input1, input2, input3, input4);
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
        @SuppressWarnings("unchecked")
        X input4 = (X) inputs[3];
        IMakeable4<TT, UT, VT, WT, XT> fn = compile(target, input1, input2, input3, input4);
        return (t, iv) -> {
            TT tData = fn.make(input1.getData(), input2.getData(), input3.getData(), input4.getData());
            t.setData(tData);
        };
    }

    IMakeable4<TT, UT, VT, WT, XT> compile(@NotNull T target, U input1, V input2, W input3, X input4)
            throws CompileException;

}
