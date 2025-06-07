package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        T extends IKeyData<K, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, T, TT>
        implements IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    public SimpleDataTypedKeyPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IDataTypedParameterizedKey<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <S, Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    Builder<S, Tp, Param, K, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT>
            extends AbstractBuilder<Builder<S, Tp, Param, K, T, TT>, Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, T, TT> {

        BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> apply;
        S subject;

        @Override
        public IDataTypedParameterizedKey<?, ?, ?>[] defaultInputs() {
            return new IDataTypedParameterizedKey<?, ?, ?>[inputCount()];
        }

        public Builder<S, Tp, Param, K, T, TT> apply(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, K, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleDataTypedKeyPatternMakeRule<>(priority, pattern, inputs, fn);
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

    public static class Dispatcher<S, Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT>
            extends SimpleDataTypedKeyPatternMakeRuleBuilders<S, Tp, Param, K, T, TT> {

        BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> apply;
        S subject;
        Tp pattern;

        public Dispatcher(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> apply, S subject, Tp pattern) {
            this.apply = apply;
            this.subject = subject;
            this.pattern = pattern;
        }

        @Override
        public BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> getApply() {
            return apply;
        }

        @Override
        public S getSubject() {
            return subject;
        }

        @Override
        public Tp getPattern() {
            return pattern;
        }

        public Dispatcher<S, Tp, Param, K, T, TT> apply(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Dispatcher<S, Tp, Param, K, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public Dispatcher<S, Tp, Param, K, T, TT> pattern(Tp pattern) {
            this.pattern = pattern;
            return this;
        }

        public Builder<S, Tp, Param, K, T, TT> input(@NotNull IDataTypedParameterizedKey<?, ?, ?>... inputss) {
            return SimpleDataTypedKeyPatternMakeRule.<S, Tp, Param, K, T, TT>builder()//
                    .pattern(pattern) //
                    .input(inputss) //
                    .apply(apply).subject(subject);
        }

    }

}
