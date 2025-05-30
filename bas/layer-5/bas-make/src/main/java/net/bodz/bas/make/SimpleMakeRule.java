package net.bodz.bas.make;

import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule<T extends IKeyData<TK, TT>, TK, TT>
        implements IMakeRule<T> {

    int priority;
    MakeFunction<T> fn;
    IDataTypedKey<?, ?>[] inputs;

    public SimpleMakeRule(int priority, @NotNull MakeFunction<T> fn, @NotNull IDataTypedKey<?, ?>... inputs) {
        this.priority = priority;
        this.fn = fn;
        this.inputs = inputs;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public IDataTypedKey<?, ?>[] getInputs() {
        return inputs;
    }

    @Override
    public void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException {
        fn.make(target, inputs);
    }

    public static <T extends IKeyData<TK, TT>, TK, TT> Builder<T, TK, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyData<TK, TT>, TK, TT> {

        int priority;
        MakeFunction<T> fn;
        IKeyData<?, ?>[] inputs = {};

        public Builder<T, TK, TT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<T, TK, TT> fn(@NotNull MakeFunction<T> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<T, TK, TT> input(@NotNull IKeyData<?, ?>... inputs) {
            this.inputs = inputs;
            return this;
        }

        public SimpleMakeRule<T, TK, TT> build() {
            if (fn == null)
                throw new NullPointerException("fn");
            if (inputs == null)
                throw new NullPointerException("inputs");
            return new SimpleMakeRule<>(priority, fn, inputs);
        }

    }

}
