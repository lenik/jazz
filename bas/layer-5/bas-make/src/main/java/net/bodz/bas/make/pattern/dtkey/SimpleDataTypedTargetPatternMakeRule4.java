package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction4;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule4;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTargetPatternMakeRule4<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
        Ws extends IDataTypedParameterizedKeys<Param, WK, WT>, WK, //
        Xs extends IDataTypedParameterizedKeys<Param, XK, XT>, XK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT> //
        extends SimpleKeyPatternLikeMakeRule4<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>
        implements IDataTypedTargetPatternMakeRule4<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> {

    public SimpleDataTypedTargetPatternMakeRule4(int priority, @NotNull Tp pattern, @NotNull CompileFunction4<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s);
    }

    public static <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKeys<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKeys<Param, XK, XT>, XK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    Builder<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKeys<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKeys<Param, XK, XT>, XK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
            extends SimpleKeyPatternLikeMakeRule4.Builder<Builder<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>, //
            Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> {

        public SimpleDataTypedTargetPatternMakeRule4<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> build() {
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
            return new SimpleDataTypedTargetPatternMakeRule4<>(priority, pattern, fn, input1s, input2s, input3s, input4s);
        }

    }

}
