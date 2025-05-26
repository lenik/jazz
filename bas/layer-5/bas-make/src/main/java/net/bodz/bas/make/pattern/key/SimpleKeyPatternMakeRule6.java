package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.fn.CompileFunction6;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule6;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule6<Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKeys<Param, UK>, UK, //
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
        extends SimpleKeyPatternLikeMakeRule6<Tp, Param, K, IParameterizedKeys<?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>
        implements IKeyPatternMakeRule6<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> {

    public SimpleKeyPatternMakeRule6(int priority, @NotNull Tp pattern, @NotNull CompileFunction6<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull Zs input6s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            Ws extends IParameterizedKeys<Param, WK>, WK, //
            Xs extends IParameterizedKeys<Param, XK>, XK, //
            Ys extends IParameterizedKeys<Param, YK>, YK, //
            Zs extends IParameterizedKeys<Param, ZK>, ZK, //
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
            , //
            Z extends IKeyData<ZK, ZT>, ZT
            > //
    Builder<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            Ws extends IParameterizedKeys<Param, WK>, WK, //
            Xs extends IParameterizedKeys<Param, XK>, XK, //
            Ys extends IParameterizedKeys<Param, YK>, YK, //
            Zs extends IParameterizedKeys<Param, ZK>, ZK, //
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
            , //
            Z extends IKeyData<ZK, ZT>, ZT
            > //
            extends SimpleKeyPatternLikeMakeRule6.Builder<Builder<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>, //
            Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> {

        public SimpleKeyPatternMakeRule6<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> build() {
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
            if (input6s == null)
                throw new NullPointerException("input6s");
            return new SimpleKeyPatternMakeRule6<>(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s);
        }

    }

}
