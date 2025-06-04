package net.bodz.bas.make.pattern.target;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.CompileFunction4;
import net.bodz.bas.make.pattern.template.SimpleTargetPatternLikeMakeRule4;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetPatternMakeRule4<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
        Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
        Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
        Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT> //
        extends SimpleTargetPatternLikeMakeRule4<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>
        implements ITargetPatternMakeRule4<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> {

    public SimpleTargetPatternMakeRule4(int priority, @NotNull Tp pattern, @NotNull CompileFunction4<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s);
    }

    public static <S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
            extends SimpleTargetPatternLikeMakeRule4.Builder<Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>, //
            Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> {

        BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> apply(BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleTargetPatternMakeRule4<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> build() {
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
            return new SimpleTargetPatternMakeRule4<>(priority, pattern, fn, input1s, input2s, input3s, input4s);
        }

        public void make(CompileFunction4<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction4<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
