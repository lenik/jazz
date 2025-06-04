package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule1<Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends SimpleKeyPatternLikeMakeRule1<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, T, TT, U, UT>
        implements IDataTypedKeyPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> {

    public SimpleDataTypedKeyPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
            extends SimpleKeyPatternLikeMakeRule1.Builder<Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT>, //
            Tp, Param, TK, Us, UK, T, TT, U, UT> {

        BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> apply(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedKeyPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleDataTypedKeyPatternMakeRule1<>(priority, pattern, fn, input1s);
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
