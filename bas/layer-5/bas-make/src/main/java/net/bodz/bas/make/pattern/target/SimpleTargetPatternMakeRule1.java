package net.bodz.bas.make.pattern.target;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.pattern.template.SimpleTargetPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetPatternMakeRule1<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedTarget<Param, U, UK, UT>, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT> //
        extends SimpleTargetPatternLikeMakeRule1<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, Us, T, TT, U, UK, UT>
        implements ITargetPatternMakeRule1<Tp, Param, TK, Us, T, TT, U, UK, UT> {

    public SimpleTargetPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT> //
    Builder<S, Tp, Param, TK, Us, T, TT, U, UK, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT> //
            extends SimpleTargetPatternLikeMakeRule1.Builder<Builder<S, Tp, Param, TK, Us, T, TT, U, UK, UT>, //
            Tp, Param, TK, Us, T, TT, U, UK, UT> {

        BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, T, TT, U, UK, UT> apply(BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, T, TT, U, UK, UT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleTargetPatternMakeRule1<Tp, Param, TK, Us, T, TT, U, UK, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleTargetPatternMakeRule1<>(priority, pattern, fn, input1s);
        }

        public void make(CompileFunction1<T, TK, TT, U, UK, UT> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction1<T, TK, TT, U, UK, UT> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
