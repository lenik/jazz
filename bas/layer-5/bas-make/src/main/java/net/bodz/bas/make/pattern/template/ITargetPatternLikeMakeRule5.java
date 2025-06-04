package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.IMakeable5;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule5;
import net.bodz.bas.meta.decl.NotNull;

public interface ITargetPatternLikeMakeRule5<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
        Keys extends IParameterizedTarget<?, ?, ?, ?>, //
        Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
        Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
        Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
        Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
        Ys extends IParameterizedTarget<Param, Y, YK, YT>, YK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT> //
        extends ITargetPatternLikeMakeRule<Tp, Param, TK, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    Us getInput1();

    Vs getInput2();

    Ws getInput3();

    Xs getInput4();

    Ys getInput5();

    @Override
    default BoundRule<T, TK, TT> compile(@NotNull T target, @NotNull IDataBinding binding)
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
        @SuppressWarnings("unchecked")
        Ys input5s = (Ys) getInputs()[4];

        Param param = pattern.match(target);
        if (param == null)
            return null;

        U input1 = input1s.getTarget(param, binding);
        if (input1 == null)
            return null;

        V input2 = input2s.getTarget(param, binding);
        if (input2 == null)
            return null;

        W input3 = input3s.getTarget(param, binding);
        if (input3 == null)
            return null;

        X input4 = input4s.getTarget(param, binding);
        if (input4 == null)
            return null;

        Y input5 = input5s.getTarget(param, binding);
        if (input5 == null)
            return null;

        IMakeable5<TT, UT, VT, WT, XT, YT> fn = compile(target, input1, input2, input3, input4, input5);
        if (fn == null)
            return null;

        SimpleMakeRule5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> rule = SimpleMakeRule5.<T, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>builder()//
                .priority(this.getPriority())//
                .input(input1, input2, input3, input4, input5)//
                .fn(fn).build();

        BoundRule<T, TK, TT> instance = new BoundRule<>(rule, target, input1, input2, input3, input4, input5);
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
        @SuppressWarnings("unchecked")
        Y input5 = (Y) inputs[4];
        IMakeable5<TT, UT, VT, WT, XT, YT> fn = compile(target, input1, input2, input3, input4, input5);
        return (t, iv) -> {
            TT tData = fn.make(input1.getData(), input2.getData(), input3.getData(), input4.getData(), input5.getData());
            t.setData(tData);
        };
    }

    IMakeable5<TT, UT, VT, WT, XT, YT> compile(@NotNull T target, U input1, V input2, W input3, X input4, Y input5)
            throws CompileException;

}
