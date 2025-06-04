package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;

public abstract class SimpleTarget2KeyPatternMakeRuleBuilders<S, Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> {

    public abstract BiConsumer<S, ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> getApply();

    public abstract S getSubject();

    public SimpleTarget2KeyPatternMakeRule0.Builder<S, Tp, Param, TK, T, TT> input() {
        return SimpleTarget2KeyPatternMakeRule0.<S, Tp, Param, TK, T, TT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input();
    }

    public <Us extends IParameterizedKey<Param, UK>, UK, //
            U extends IKeyData<UK, UT>, UT> //
    SimpleTarget2KeyPatternMakeRule1.Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> input(Us input1s) {
        return SimpleTarget2KeyPatternMakeRule1.<S, Tp, Param, TK, Us, UK, T, TT, U, UT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1s);
    }

    public <Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    SimpleTarget2KeyPatternMakeRule2.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> input(Us input1s, Vs input2s) {
        return SimpleTarget2KeyPatternMakeRule2.<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1s, input2s);
    }

    public <Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    SimpleTarget2KeyPatternMakeRule3.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> input(Us input1s, Vs input2s, Ws input3s) {
        return SimpleTarget2KeyPatternMakeRule3.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1s, input2s, input3s);
    }

    public <Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    SimpleTarget2KeyPatternMakeRule4.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s) {
        return SimpleTarget2KeyPatternMakeRule4.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1s, input2s, input3s, input4s);
    }

    public <Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
    SimpleTarget2KeyPatternMakeRule5.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s, Ys input5s) {
        return SimpleTarget2KeyPatternMakeRule5.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1s, input2s, input3s, input4s, input5s);
    }

    public <Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            Zs extends IParameterizedKey<Param, ZK>, ZK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    SimpleTarget2KeyPatternMakeRule6.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s, Ys input5s, Zs input6s) {
        return SimpleTarget2KeyPatternMakeRule6.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public <U1s extends IParameterizedKey<Param, U1K>, U1K, //
            U2s extends IParameterizedKey<Param, U2K>, U2K, //
            U3s extends IParameterizedKey<Param, U3K>, U3K, //
            U4s extends IParameterizedKey<Param, U4K>, U4K, //
            U5s extends IParameterizedKey<Param, U5K>, U5K, //
            U6s extends IParameterizedKey<Param, U6K>, U6K, //
            U7s extends IParameterizedKey<Param, U7K>, U7K, //
            U1 extends IKeyData<U1K, U1T>, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7T> //
    SimpleTarget2KeyPatternMakeRule7.Builder<S, Tp, Param, TK, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T> input(U1s input1s, U2s input2s, U3s input3s, U4s input4s, U5s input5s, U6s input6s, U7s input7s) {
        return SimpleTarget2KeyPatternMakeRule7.<S, Tp, Param, TK, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s);
    }

}
