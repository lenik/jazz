package net.bodz.bas.make.pattern.target;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;

public abstract class SimpleTargetPatternMakeRuleBuilders<S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> {

    public abstract BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> getApply();

    public abstract S getSubject();

    public abstract Tp getPattern();

    public SimpleTargetPatternMakeRule0.Builder<S, Tp, Param, TK, T, TT> input() {
        return SimpleTargetPatternMakeRule0.<S, Tp, Param, TK, T, TT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) 
                .input();
    }

    public <Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            U extends IKeyData<UK, UT>, UT> //
    SimpleTargetPatternMakeRule1.Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> input(Us input1s) {
        return SimpleTargetPatternMakeRule1.<S, Tp, Param, TK, Us, UK, T, TT, U, UT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) 
                .input(input1s);
    }

    public <Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    SimpleTargetPatternMakeRule2.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> input(Us input1s, Vs input2s) {
        return SimpleTargetPatternMakeRule2.<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) 
                .input(input1s, input2s);
    }

    public <Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    SimpleTargetPatternMakeRule3.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> input(Us input1s, Vs input2s, Ws input3s) {
        return SimpleTargetPatternMakeRule3.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) 
                .input(input1s, input2s, input3s);
    }

    public <Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    SimpleTargetPatternMakeRule4.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s) {
        return SimpleTargetPatternMakeRule4.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) 
                .input(input1s, input2s, input3s, input4s);
    }

    public <Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
            Ys extends IParameterizedTarget<Param, Y, YK, YT>, YK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
    SimpleTargetPatternMakeRule5.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s, Ys input5s) {
        return SimpleTargetPatternMakeRule5.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) 
                .input(input1s, input2s, input3s, input4s, input5s);
    }

    public <Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
            Ys extends IParameterizedTarget<Param, Y, YK, YT>, YK, //
            Zs extends IParameterizedTarget<Param, Z, ZK, ZT>, ZK, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    SimpleTargetPatternMakeRule6.Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> input(Us input1s, Vs input2s, Ws input3s, Xs input4s, Ys input5s, Zs input6s) {
        return SimpleTargetPatternMakeRule6.<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) 
                .input(input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public <U1s extends IParameterizedTarget<Param, U1, U1K, U1T>, U1K, //
            U2s extends IParameterizedTarget<Param, U2, U2K, U2T>, U2K, //
            U3s extends IParameterizedTarget<Param, U3, U3K, U3T>, U3K, //
            U4s extends IParameterizedTarget<Param, U4, U4K, U4T>, U4K, //
            U5s extends IParameterizedTarget<Param, U5, U5K, U5T>, U5K, //
            U6s extends IParameterizedTarget<Param, U6, U6K, U6T>, U6K, //
            U7s extends IParameterizedTarget<Param, U7, U7K, U7T>, U7K, //
            U1 extends IKeyData<U1K, U1T>, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7T> //
    SimpleTargetPatternMakeRule7.Builder<S, Tp, Param, TK, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T> input(U1s input1s, U2s input2s, U3s input3s, U4s input4s, U5s input5s, U6s input6s, U7s input7s) {
        return SimpleTargetPatternMakeRule7.<S, Tp, Param, TK, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>builder()//
                .apply(getApply()).subject(getSubject()) //
                .pattern(getPattern()) 
                .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s);
    }

}
