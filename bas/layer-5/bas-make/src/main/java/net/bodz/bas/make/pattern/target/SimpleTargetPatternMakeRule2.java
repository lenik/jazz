package net.bodz.bas.make.pattern.target;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.pattern.template.SimpleTargetPatternLikeMakeRule2;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetPatternMakeRule2<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedTarget<Param, U, UK, UT>, //
        Vs extends IParameterizedTarget<Param, V, VK, VT>, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT> //
        extends SimpleTargetPatternLikeMakeRule2<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, Us, Vs, T, TT, U, UK, UT, V, VK, VT>
        implements ITargetPatternMakeRule2<Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> {

    public SimpleTargetPatternMakeRule2(int priority, @NotNull Tp pattern, @NotNull CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn, @NotNull Us input1s, @NotNull Vs input2s) {
        super(priority, pattern, fn, input1s, input2s);
    }

    public static <S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
    Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
            extends SimpleTargetPatternLikeMakeRule2.Builder<Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT>, //
            Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> {

        BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> apply(BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleTargetPatternMakeRule2<Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            return new SimpleTargetPatternMakeRule2<>(priority, pattern, fn, input1s, input2s);
        }

        public void make(CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
