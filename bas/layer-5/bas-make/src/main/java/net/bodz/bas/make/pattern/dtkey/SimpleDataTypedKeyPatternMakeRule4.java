package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction4;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule4;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule4<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
        Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT> //
        extends SimpleKeyPatternLikeMakeRule4<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>
        implements IDataTypedKeyPatternMakeRule4<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> {

    public SimpleDataTypedKeyPatternMakeRule4(int priority, @NotNull Tp pattern, @NotNull CompileFunction4<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s);
    }

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    Builder<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
            extends SimpleKeyPatternLikeMakeRule4.Builder<Builder<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>, //
            Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> {

        public SimpleDataTypedKeyPatternMakeRule4<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> build() {
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
            return new SimpleDataTypedKeyPatternMakeRule4<>(priority, pattern, fn, input1s, input2s, input3s, input4s);
        }

    }

}
