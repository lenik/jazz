package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;

public abstract class SimpleKeyPatternMakeRuleBuilders<S, Tp extends IKeyPattern<Param, TK>, Param, TK, T extends IKeyData<TK, TT>, TT> {

    public abstract BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> getApply();

    public abstract S getSubject();

    public abstract Tp getPattern();

    public SimpleKeyPatternMakeRule0.Builder<S, Tp, Param, TK, T, TT> input() {
        return SimpleKeyPatternMakeRule0.<S, Tp, Param, TK, T, TT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input();
    }

    public <Us extends IParameterizedKey<Param, UK>, //
            U extends IKeyData<UK, UT>, UK, UT>SimpleKeyPatternMakeRule1.Builder<S, Tp, Param, TK, Us, T, TT, U, UK, UT> input(Us input1s) {
        return SimpleKeyPatternMakeRule1.<S, Tp, Param, TK, Us, T, TT, U, UK, UT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s);
    }

    public <Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT>SimpleKeyPatternMakeRule2.Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> input(Us input1s, Vs input2s) {
        return SimpleKeyPatternMakeRule2.<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s);
    }

    public <Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            Ws extends IParameterizedKey<Param, WK>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT>SimpleKeyPatternMakeRule3.Builder<S, Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT> input(Us input1s, Vs input2s, Ws input3s) {
        return SimpleKeyPatternMakeRule3.<S, Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s);
    }

    public <Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            Ws extends IParameterizedKey<Param, WK>, //
            Xs extends IParameterizedKey<Param, XK>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT>SimpleKeyPatternMakeRule4.Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s) {
        return SimpleKeyPatternMakeRule4.<S, Tp, Param, TK, Us, Vs, Ws, Xs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s);
    }

    public <Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            Ws extends IParameterizedKey<Param, WK>, //
            Xs extends IParameterizedKey<Param, XK>, //
            Ys extends IParameterizedKey<Param, YK>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT>SimpleKeyPatternMakeRule5.Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s, Ys input5s) {
        return SimpleKeyPatternMakeRule5.<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s);
    }

    public <Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            Ws extends IParameterizedKey<Param, WK>, //
            Xs extends IParameterizedKey<Param, XK>, //
            Ys extends IParameterizedKey<Param, YK>, //
            Zs extends IParameterizedKey<Param, ZK>, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT>SimpleKeyPatternMakeRule6.Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s, Ys input5s, Zs input6s) {
        return SimpleKeyPatternMakeRule6.<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public <U1s extends IParameterizedKey<Param, U1K>, //
            U2s extends IParameterizedKey<Param, U2K>, //
            U3s extends IParameterizedKey<Param, U3K>, //
            U4s extends IParameterizedKey<Param, U4K>, //
            U5s extends IParameterizedKey<Param, U5K>, //
            U6s extends IParameterizedKey<Param, U6K>, //
            U7s extends IParameterizedKey<Param, U7K>, //
            U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7K, U7T>SimpleKeyPatternMakeRule7.Builder<S, Tp, Param, TK, U1s, U2s, U3s, U4s, U5s, U6s, U7s, T, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input(U1s input1s, U2s input2s, U3s input3s, U4s input4s, U5s input5s, U6s input6s, U7s input7s) {
        return SimpleKeyPatternMakeRule7.<S, Tp, Param, TK, U1s, U2s, U3s, U4s, U5s, U6s, U7s, T, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) //
                .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s);
    }

}
