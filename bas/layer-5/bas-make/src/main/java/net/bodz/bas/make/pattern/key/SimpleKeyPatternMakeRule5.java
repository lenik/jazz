package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction5;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule5;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule5<Tp extends IKeyPattern<Param, TK>, Param, TK, Us extends IParameterizedKey<Param, UK>, //
        Vs extends IParameterizedKey<Param, VK>, //
        Ws extends IParameterizedKey<Param, WK>, //
        Xs extends IParameterizedKey<Param, XK>, //
        Ys extends IParameterizedKey<Param, YK>, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT, //
        Y extends IKeyData<YK, YT>, YK, YT> //
        extends SimpleKeyPatternLikeMakeRule5<Tp, Param, TK, IParameterizedKey<?, ?>, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>
        implements IKeyPatternMakeRule5<Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> {

    public SimpleKeyPatternMakeRule5(int priority, @NotNull Tp pattern, @NotNull CompileFunction5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s);
    }

    public static <S, Tp extends IKeyPattern<Param, TK>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            Ws extends IParameterizedKey<Param, WK>, //
            Xs extends IParameterizedKey<Param, XK>, //
            Ys extends IParameterizedKey<Param, YK>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT> //
    Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IKeyPattern<Param, TK>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            Ws extends IParameterizedKey<Param, WK>, //
            Xs extends IParameterizedKey<Param, XK>, //
            Ys extends IParameterizedKey<Param, YK>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT> //
            extends SimpleKeyPatternLikeMakeRule5.Builder<Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>, //
            Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> {

        BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> apply(BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleKeyPatternMakeRule5<Tp, Param, TK, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> build() {
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
