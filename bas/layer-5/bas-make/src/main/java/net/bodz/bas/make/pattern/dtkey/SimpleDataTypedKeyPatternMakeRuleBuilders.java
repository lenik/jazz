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

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            U extends IKeyData<UK, UT>, UK, UT>SimpleDataTypedKeyPatternMakeRule1.Builder<S, Tp, Param, TK, Us, T, TT, U, UK, UT> input(IDataTypedParameterizedKey<Param, UK, UT> input1s) {
        return SimpleDataTypedKeyPatternMakeRule1.<S, Tp, Param, TK, Us, T, TT, U, UK, UT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT>SimpleDataTypedKeyPatternMakeRule2.Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> input(IDataTypedParameterizedKey<Param, UK, UT> input1s, 
    IDataTypedParameterizedKey<Param, VK, VT> input2s) {
        return SimpleDataTypedKeyPatternMakeRule2.<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT>SimpleDataTypedKeyPatternMakeRule3.Builder<S, Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT> input(IDataTypedParameterizedKey<Param, UK, UT> input1s, 
    IDataTypedParameterizedKey<Param, VK, VT> input2s, 
    IDataTypedParameterizedKey<Param, WK, WT> input3s) {
        return SimpleDataTypedKeyPatternMakeRule3.<S, Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT>SimpleDataTypedKeyPatternMakeRule4.Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> input(IDataTypedParameterizedKey<Param, UK, UT> input1s, 
    IDataTypedParameterizedKey<Param, VK, VT> input2s, 
    IDataTypedParameterizedKey<Param, WK, WT> input3s, 
    IDataTypedParameterizedKey<Param, XK, XT> input4s) {
        return SimpleDataTypedKeyPatternMakeRule4.<S, Tp, Param, TK, Us, Vs, Ws, Xs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT>SimpleDataTypedKeyPatternMakeRule5.Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input(IDataTypedParameterizedKey<Param, UK, UT> input1s, 
    IDataTypedParameterizedKey<Param, VK, VT> input2s, 
    IDataTypedParameterizedKey<Param, WK, WT> input3s, 
    IDataTypedParameterizedKey<Param, XK, XT> input4s, 
    IDataTypedParameterizedKey<Param, YK, YT> input5s) {
        return SimpleDataTypedKeyPatternMakeRule5.<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s);
    }

    public <Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, //
            Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT>SimpleDataTypedKeyPatternMakeRule6.Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input(IDataTypedParameterizedKey<Param, UK, UT> input1s, 
    IDataTypedParameterizedKey<Param, VK, VT> input2s, 
    IDataTypedParameterizedKey<Param, WK, WT> input3s, 
    IDataTypedParameterizedKey<Param, XK, XT> input4s, 
    IDataTypedParameterizedKey<Param, YK, YT> input5s, 
    IDataTypedParameterizedKey<Param, ZK, ZT> input6s) {
        return SimpleDataTypedKeyPatternMakeRule6.<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public <U1s extends IDataTypedParameterizedKey<Param, U1K, U1T>, //
            U2s extends IDataTypedParameterizedKey<Param, U2K, U2T>, //
            U3s extends IDataTypedParameterizedKey<Param, U3K, U3T>, //
            U4s extends IDataTypedParameterizedKey<Param, U4K, U4T>, //
            U5s extends IDataTypedParameterizedKey<Param, U5K, U5T>, //
            U6s extends IDataTypedParameterizedKey<Param, U6K, U6T>, //
            U7s extends IDataTypedParameterizedKey<Param, U7K, U7T>, //
            U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7K, U7T>SimpleDataTypedKeyPatternMakeRule7.Builder<S, Tp, Param, TK, U1s, U2s, U3s, U4s, U5s, U6s, U7s, T, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input(IDataTypedParameterizedKey<Param, U1K, U1T> input1s, 
    IDataTypedParameterizedKey<Param, U2K, U2T> input2s, 
    IDataTypedParameterizedKey<Param, U3K, U3T> input3s, 
    IDataTypedParameterizedKey<Param, U4K, U4T> input4s, 
    IDataTypedParameterizedKey<Param, U5K, U5T> input5s, 
    IDataTypedParameterizedKey<Param, U6K, U6T> input6s, 
    IDataTypedParameterizedKey<Param, U7K, U7T> input7s) {
        return SimpleDataTypedKeyPatternMakeRule7.<S, Tp, Param, TK, U1s, U2s, U3s, U4s, U5s, U6s, U7s, T, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s);
    }

}
