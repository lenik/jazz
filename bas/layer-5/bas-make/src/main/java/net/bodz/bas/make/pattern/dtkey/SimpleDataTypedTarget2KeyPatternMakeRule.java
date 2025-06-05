package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTarget2KeyPatternMakeRule<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, T, TT>
        implements IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> {

    public SimpleDataTypedTarget2KeyPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IDataTypedParameterizedKey<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <S, Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    Builder<S, Tp, Param, TK, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT>
            extends AbstractBuilder<Builder<S, Tp, Param, TK, T, TT>, Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, T, TT> {

        BiConsumer<S, IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, T, TT> apply(BiConsumer<S, IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IDataTypedParameterizedKey<?, ?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleDataTypedTarget2KeyPatternMakeRule<>(priority, pattern, inputs, fn);
        }

        public void make(CompileFunction<T> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction<T> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

    public static class Dispatcher<S, Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT>
            extends SimpleDataTypedTarget2KeyPatternMakeRuleBuilders<S, Tp, Param, TK, T, TT> {

        BiConsumer<S, IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Dispatcher(BiConsumer<S, IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply, S subject) {
            this.apply = apply;
            this.subject = subject;
        }

        @Override
        public BiConsumer<S, IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> getApply() {
            return apply;
        }

        @Override
        public S getSubject() {
            return subject;
        }

        public Dispatcher<S, Tp, Param, TK, T, TT> apply(BiConsumer<S, IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Dispatcher<S, Tp, Param, TK, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedTarget2KeyPatternMakeRule.Builder<S, Tp, Param, TK, T, TT> input(@NotNull IDataTypedParameterizedKey<?, ?, ?>... inputss) {
            return SimpleDataTypedTarget2KeyPatternMakeRule.<S, Tp, Param, TK, T, TT>builder()//
                    .input(inputss) //
                    .apply(apply).subject(subject);
        }

    }

}
