package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;

public abstract class SimpleDataTypedKeyPatternMakeRuleBuilders<S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> {

    public abstract BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> getApply();

    public abstract S getSubject();

    public abstract Tp getPattern();

    public SimpleDataTypedKeyPatternMakeRule0.Builder<S, Tp, Param, TK, T, TT> input() {
        return SimpleDataTypedKeyPatternMakeRule0.<S, Tp, Param, TK, T, TT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input();
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            U extends IKeyData<UK, UT>, UT> //
    SimpleDataTypedKeyPatternMakeRule1.Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> input(Us input1s) {
        return SimpleDataTypedKeyPatternMakeRule1.<S, Tp, Param, TK, Us, UK, T, TT, U, UT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    SimpleDataTypedKeyPatternMakeRule2.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> input(Us input1s, Vs input2s) {
        return SimpleDataTypedKeyPatternMakeRule2.<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    SimpleDataTypedKeyPatternMakeRule3.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> input(Us input1s, Vs input2s, Ws input3s) {
        return SimpleDataTypedKeyPatternMakeRule3.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    SimpleDataTypedKeyPatternMakeRule4.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s) {
        return SimpleDataTypedKeyPatternMakeRule4.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
    SimpleDataTypedKeyPatternMakeRule5.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s, Ys input5s) {
        return SimpleDataTypedKeyPatternMakeRule5.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
            Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, ZK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    SimpleDataTypedKeyPatternMakeRule6.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s, Ys input5s, Zs input6s) {
        return SimpleDataTypedKeyPatternMakeRule6.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public <U1s extends IDataTypedParameterizedKey<Param, U1K, U1T>, U1K, //
            U2s extends IDataTypedParameterizedKey<Param, U2K, U2T>, U2K, //
            U3s extends IDataTypedParameterizedKey<Param, U3K, U3T>, U3K, //
            U4s extends IDataTypedParameterizedKey<Param, U4K, U4T>, U4K, //
            U5s extends IDataTypedParameterizedKey<Param, U5K, U5T>, U5K, //
            U6s extends IDataTypedParameterizedKey<Param, U6K, U6T>, U6K, //
            U7s extends IDataTypedParameterizedKey<Param, U7K, U7T>, U7K, //
            U1 extends IKeyData<U1K, U1T>, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7T> //
    SimpleDataTypedKeyPatternMakeRule7.Builder<S, Tp, Param, TK, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T> input(U1s input1s, U2s input2s, U3s input3s, U4s input4s, U5s input5s, U6s input6s, U7s input7s) {
        return SimpleDataTypedKeyPatternMakeRule7.<S, Tp, Param, TK, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s);
    }

}
