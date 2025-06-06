package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule<Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, K, IParameterizedKey<?, ?>, T, TT>
        implements IKeyPatternMakeRule<Tp, Param, K, T, TT> {

    public SimpleKeyPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <S, Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    Builder<S, Tp, Param, K, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
            extends AbstractBuilder<Builder<S, Tp, Param, K, T, TT>, //
            Tp, Param, K, IParameterizedKey<?, ?>, T, TT> {

        BiConsumer<S, IKeyPatternMakeRule<Tp, Param, K, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, K, T, TT> apply(BiConsumer<S, IKeyPatternMakeRule<Tp, Param, K, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, K, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleKeyPatternMakeRule<Tp, Param, K, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IParameterizedKey<?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleKeyPatternMakeRule<>(priority, pattern, inputs, fn);
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

    public static class Dispatcher<S, Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
            extends SimpleKeyPatternMakeRuleBuilders<S, Tp, Param, K, T, TT> {

        BiConsumer<S, IKeyPatternMakeRule<Tp, Param, K, T, TT>> apply;
        S subject;
        Tp pattern;

        public Dispatcher(BiConsumer<S, IKeyPatternMakeRule<Tp, Param, K, T, TT>> apply, S subject, Tp pattern) {
            this.apply = apply;
            this.subject = subject;
            this.pattern = pattern;
        }

        @Override
        public BiConsumer<S, IKeyPatternMakeRule<Tp, Param, K, T, TT>> getApply() {
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

        public Dispatcher<S, Tp, Param, K, T, TT> apply(BiConsumer<S, IKeyPatternMakeRule<Tp, Param, K, T, TT>> apply) {
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

        public SimpleKeyPatternMakeRule.Builder<S, Tp, Param, K, T, TT> input(@NotNull IParameterizedKey<?, ?>... inputss) {
            return SimpleKeyPatternMakeRule.<S, Tp, Param, K, T, TT>builder()//
                    .pattern(pattern) //
                    .input(inputss) //
                    .apply(apply).subject(subject);
        }

    }

}