package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule<T extends IKeyData<?, ?>>
        implements IMakeRule<T> {

    int priority;
    IKeyData<?, ?>[] inputs;
    MakeFunction<T> fn;

    public SimpleMakeRule(int priority, @NotNull IKeyData<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        this.priority = priority;
        this.inputs = inputs;
        this.fn = fn;
    }

    @Override
    public int getPriority() {
        return priority;
    }


    @NotNull
    @Override
    public IKeyData<?, ?>[] getInputs() {
        return inputs;
    }

    @Override
    public void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException {
        fn.make(target, inputs);
    }

    public static <T extends IKeyData<?, ?>> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyData<?, ?>> {

        int priority;
        IKeyData<?, ?>[] inputs = {};
        MakeFunction<T> fn;


        public Builder<T> priority(int priority) {
            this.priority = priority;
            return this;
        }


        public Builder<T> input(@NotNull IKeyData<?, ?>... inputs) {
            this.inputs = inputs;
            return this;
        }

        public Builder<T> make(@NotNull MakeFunction<T> fn) {
            this.fn = fn;
            return this;
        }

        public SimpleMakeRule<T> build() {
            if (inputs == null)
                throw new NullPointerException("inputs");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleMakeRule<>(priority, inputs, fn);
        }

    }

}
