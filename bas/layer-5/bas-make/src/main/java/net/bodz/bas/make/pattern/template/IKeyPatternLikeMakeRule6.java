package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeSession;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.MakeAction;
import net.bodz.bas.make.fn.IMakeable6;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule6;
import net.bodz.bas.meta.decl.NotNull;

public interface IKeyPatternLikeMakeRule6<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        Ws extends IParameterizedKeys<Param, WK>, WK, //
        Xs extends IParameterizedKeys<Param, XK>, XK, //
        Ys extends IParameterizedKeys<Param, YK>, YK, //
        Zs extends IParameterizedKeys<Param, ZK>, ZK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT, //
        Z extends IKeyData<ZK, ZT>, ZT> //
        extends IKeyPatternLikeMakeRule<Tp, Param, K, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    Us getInput1();

    Vs getInput2();

    Ws getInput3();

    Xs getInput4();

    Ys getInput5();

    Zs getInput6();

    @Override
    default MakeAction<T> compile(@NotNull T target, @NotNull IMakeSession session)
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
        @SuppressWarnings("unchecked")
        Zs input6s = (Zs) getInputs()[5];

        Param param = pattern.match(target.getKey());
        if (param == null)
            return null;

        UK input1Key = input1s.getKey(param);
        if (input1Key == null)
            return null;

        @SuppressWarnings("unchecked")
        U input1 = (U) session.getData(input1Key);
        if (input1 == null)
            return null;

        VK input2Key = input2s.getKey(param);
        if (input2Key == null)
            return null;

        @SuppressWarnings("unchecked")
        V input2 = (V) session.getData(input2Key);
        if (input2 == null)
            return null;

        WK input3Key = input3s.getKey(param);
        if (input3Key == null)
            return null;

        @SuppressWarnings("unchecked")
        W input3 = (W) session.getData(input3Key);
        if (input3 == null)
            return null;

        XK input4Key = input4s.getKey(param);
        if (input4Key == null)
            return null;

        @SuppressWarnings("unchecked")
        X input4 = (X) session.getData(input4Key);
        if (input4 == null)
            return null;

        YK input5Key = input5s.getKey(param);
        if (input5Key == null)
            return null;

        @SuppressWarnings("unchecked")
        Y input5 = (Y) session.getData(input5Key);
        if (input5 == null)
            return null;

        ZK input6Key = input6s.getKey(param);
        if (input6Key == null)
            return null;

        @SuppressWarnings("unchecked")
        Z input6 = (Z) session.getData(input6Key);
        if (input6 == null)
            return null;

        IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn = compile(target, input1, input2, input3, input4, input5, input6);
        if (fn == null)
            return null;

        SimpleMakeRule6<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> rule = SimpleMakeRule6.<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>builder()//
                .priority(this.getPriority())//
                .fn(fn).build();

        MakeAction<T> instance = new MakeAction<>(rule, target, input1, input2, input3, input4, input5, input6);
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
        @SuppressWarnings("unchecked")
        Z input6 = (Z) inputs[5];
        IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn = compile(target, input1, input2, input3, input4, input5, input6);
        return (t, iv) -> {
            TT tData = fn.make(input1.getData(), input2.getData(), input3.getData(), input4.getData(), input5.getData(), input6.getData());
            t.setData(tData);
        };
    }

    IMakeable6<TT, UT, VT, WT, XT, YT, ZT> compile(@NotNull T target, U input1, V input2, W input3, X input4, Y input5, Z input6)
            throws CompileException;

}
