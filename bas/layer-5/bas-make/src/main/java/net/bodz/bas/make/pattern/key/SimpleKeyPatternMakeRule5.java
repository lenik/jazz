package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction5;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule5;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule5<Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKey<Param, UK>, UK, //
        Vs extends IParameterizedKey<Param, VK>, VK, //
        Ws extends IParameterizedKey<Param, WK>, WK, //
        Xs extends IParameterizedKey<Param, XK>, XK, //
        Ys extends IParameterizedKey<Param, YK>, YK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT> //
        extends SimpleKeyPatternLikeMakeRule5<Tp, Param, K, IParameterizedKey<?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>
        implements IKeyPatternMakeRule5<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> {

    public SimpleKeyPatternMakeRule5(int priority, @NotNull Tp pattern, @NotNull CompileFunction5<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            , //
            V extends IKeyData<VK, VT>, VT
            , //
            W extends IKeyData<WK, WT>, WT
            , //
            X extends IKeyData<XK, XT>, XT
            , //
            Y extends IKeyData<YK, YT>, YT
            > //
    Builder<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            , //
            V extends IKeyData<VK, VT>, VT
            , //
            W extends IKeyData<WK, WT>, WT
            , //
            X extends IKeyData<XK, XT>, XT
            , //
            Y extends IKeyData<YK, YT>, YT
            > //
            extends SimpleKeyPatternLikeMakeRule5.Builder<Builder<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>, //
            Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> {

        public SimpleKeyPatternMakeRule5<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            if (input3s == null)
                throw new NullPointerException("input3s");
            if (input4s == null)
                throw new NullPointerException("input4s");
            if (input5s == null)
                throw new NullPointerException("input5s");
            return new SimpleKeyPatternMakeRule5<>(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s);
        }

    }

}
