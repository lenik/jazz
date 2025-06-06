package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetTypedKeyPatternMakeRule<Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, TK, IParameterizedKey<?, ?>, T, TT>
        implements ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> {

    public SimpleTargetTypedKeyPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <S, Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    Builder<S, Tp, Param, TK, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT>
            extends AbstractBuilder<Builder<S, Tp, Param, TK, T, TT>, //
            Tp, Param, TK, IParameterizedKey<?, ?>, T, TT> {

        BiConsumer<S, ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, T, TT> apply(BiConsumer<S, ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleTargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IParameterizedKey<?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleTargetTypedKeyPatternMakeRule<>(priority, pattern, inputs, fn);
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

    public static class Dispatcher<S, Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT>
            extends SimpleTargetTypedKeyPatternMakeRuleBuilders<S, Tp, Param, TK, T, TT> {

        BiConsumer<S, ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;
        Tp pattern;

        public Dispatcher(BiConsumer<S, ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply, S subject, Tp pattern) {
            this.apply = apply;
            this.subject = subject;
            this.pattern = pattern;
        }

        @Override
        public BiConsumer<S, ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> getApply() {
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

        public Dispatcher<S, Tp, Param, TK, T, TT> apply(BiConsumer<S, ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Dispatcher<S, Tp, Param, TK, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public Dispatcher<S, Tp, Param, TK, T, TT> pattern(Tp pattern) {
            this.pattern = pattern;
            return this;
        }

        public Builder<S, Tp, Param, TK, T, TT> input(@NotNull IParameterizedKey<?, ?>... inputss) {
            return SimpleTargetTypedKeyPatternMakeRule.<S, Tp, Param, TK, T, TT>builder()//
                    .pattern(pattern) //
                    .input(inputss) //
                    .apply(apply).subject(subject);
        }

    }

}
