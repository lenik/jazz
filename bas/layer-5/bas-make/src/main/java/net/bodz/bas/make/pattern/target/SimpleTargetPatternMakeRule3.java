package net.bodz.bas.make.pattern.target;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.CompileFunction3;
import net.bodz.bas.make.pattern.template.SimpleTargetPatternLikeMakeRule3;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetPatternMakeRule3<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
        Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
        Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT> //
        extends SimpleTargetPatternLikeMakeRule3<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>
        implements ITargetPatternMakeRule3<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> {

    public SimpleTargetPatternMakeRule3(int priority, @NotNull Tp pattern, @NotNull CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s) {
        super(priority, pattern, fn, input1s, input2s, input3s);
    }

    public static <S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
            extends SimpleTargetPatternLikeMakeRule3.Builder<Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>, //
            Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> {

        BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> apply(BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleTargetPatternMakeRule3<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> build() {
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
            return new SimpleTargetPatternMakeRule3<>(priority, pattern, fn, input1s, input2s, input3s);
        }

        public void make(CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
