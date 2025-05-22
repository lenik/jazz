package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class SimplePatternMakeRule<T extends IKeyPattern<?, K>, K, D extends IKeyData<K, ?>>
        implements IPatternMakeRule<T, K, D> {

    int priority;
    T pattern;
    IParameterizedKeys<?, ?>[] inputss;
    CompileFunction<T, K, D> fn;

    public SimplePatternMakeRule(int priority, @NotNull T pattern, @NotNull IParameterizedKeys<?, ?>[] inputss, @NotNull CompileFunction<T, K, D> fn) {
        this.priority = priority;
        this.pattern = pattern;
        this.inputss = inputss;
        this.fn = fn;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public T getPattern() {
        return pattern;
    }

    @Override
    public IParameterizedKeys<?, ?>[] getInputs() {
        return inputss;
    }

    @Override
    public MakeRuleInstance<D> compile(@NotNull D target, @NotNull IMakeSession session)
            throws MakeException {
        return fn.compile(target, pattern, inputss);
    }

    public static <T extends IKeyPattern<?, K>, K, D extends IKeyData<K, ?>> Builder<T, K, D> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyPattern<?, K>, K, D extends IKeyData<K, ?>> {

        int priority;
        T pattern;
        IParameterizedKeys<?, ?>[] inputs = {};
        CompileFunction<T, K, D> fn;

        public Builder<T, K, D> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<T, K, D> pattern(T pattern) {
            this.pattern = pattern;
            return this;
        }

        public Builder<T, K, D> input(@NotNull IParameterizedKeys<?, ?>... inputs) {
            this.inputs = inputs;
            return this;
        }

        public Builder<T, K, D> fn(CompileFunction<T, K, D> fn) {
            this.fn = fn;
            return this;
        }

        public SimplePatternMakeRule<T, K, D> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimplePatternMakeRule<>(priority, pattern, inputs, fn);
        }

    }

}
