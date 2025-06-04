package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.fn.CompileFunction5;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule5;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule5<Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
        Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
        Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT> //
        extends SimpleKeyPatternLikeMakeRule5<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>
        implements IDataTypedKeyPatternMakeRule5<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> {

    public SimpleDataTypedKeyPatternMakeRule5(int priority, @NotNull Tp pattern, @NotNull CompileFunction5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s);
    }

    public static <S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
    Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
            extends SimpleKeyPatternLikeMakeRule5.Builder<Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>, //
            Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> {

        BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> apply(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedKeyPatternMakeRule5<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT> build() {
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
            return new SimpleDataTypedKeyPatternMakeRule5<>(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s);
        }

        public void make(CompileFunction5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
