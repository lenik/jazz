package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule1<Tp extends IKeyPattern<Param, TK>, Param, TK, Us extends IParameterizedKey<Param, UK>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends SimpleKeyPatternLikeMakeRule1<Tp, Param, TK, IParameterizedKey<?, ?>, Us, UK, T, TT, U, UT>
        implements IKeyPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> {

    public SimpleKeyPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <S, Tp extends IKeyPattern<Param, TK>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IKeyPattern<Param, TK>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
            extends SimpleKeyPatternLikeMakeRule1.Builder<Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT>, //
            Tp, Param, TK, Us, UK, T, TT, U, UT> {

        BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> apply(BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleKeyPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleKeyPatternMakeRule1<>(priority, pattern, fn, input1s);
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
