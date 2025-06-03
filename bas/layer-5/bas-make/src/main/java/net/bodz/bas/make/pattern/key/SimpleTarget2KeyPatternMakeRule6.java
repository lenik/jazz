package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction6;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule6;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTarget2KeyPatternMakeRule6<Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, Us extends IParameterizedKey<Param, UK>, UK, //
        Vs extends IParameterizedKey<Param, VK>, VK, //
        Ws extends IParameterizedKey<Param, WK>, WK, //
        Xs extends IParameterizedKey<Param, XK>, XK, //
        Ys extends IParameterizedKey<Param, YK>, YK, //
        Zs extends IParameterizedKey<Param, ZK>, ZK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT, //
        Z extends IKeyData<ZK, ZT>, ZT> //
        extends SimpleKeyPatternLikeMakeRule6<Tp, Param, TK, IParameterizedKey<?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>
        implements ITarget2KeyPatternMakeRule6<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> {

    public SimpleTarget2KeyPatternMakeRule6(int priority, @NotNull Tp pattern, @NotNull CompileFunction6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull Zs input6s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public static <Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            Zs extends IParameterizedKey<Param, ZK>, ZK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    Builder<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            Zs extends IParameterizedKey<Param, ZK>, ZK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
            extends SimpleKeyPatternLikeMakeRule6.Builder<Builder<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>, //
            Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> {

        public SimpleTarget2KeyPatternMakeRule6<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> build() {
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
            return new SimpleTarget2KeyPatternMakeRule6<>(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s);
        }

    }

}
