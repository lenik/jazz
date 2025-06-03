package net.bodz.bas.make;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule<T extends IKeyData<TK, TT>, TK, TT>
        implements IMakeRule<T, TK, TT> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    MakeFunction<T> fn;
    IDataTypedKey<?, ?>[] inputs;

    public SimpleMakeRule(int priority, //
            @NotNull Class<? extends T> targetType, //
            @NotNull MakeFunction<T> fn, @NotNull IDataTypedKey<?, ?>... inputs) {
        this.priority = priority;
        this.targetType = targetType;
        this.keyType = TypeParam.infer1(targetType, IKeyData.class, 0);
        this.dataType = TypeParam.infer1(targetType, IKeyData.class, 1);
        this.fn = fn;
        this.inputs = inputs;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @NotNull
    @Override
    public Class<? extends T> getTargetType() {
        return targetType;
    }

    @NotNull
    @Override
    public Class<? extends TK> getKeyType() {
        return keyType;
    }

    @NotNull
    @Override
    public Class<? extends TT> getDataType() {
        return dataType;
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
        Class<? extends T> targetType;
        Class<? extends TK> keyType;
        Class<? extends TT> dataType;
        MakeFunction<T> fn;
        IKeyData<?, ?>[] inputs = {};

        public Builder<T, TK, TT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<T, TK, TT> targetType(Class<? extends T> targetType) {
            this.targetType = targetType;
            return this;
        }

        public Builder<T, TK, TT> keyType(Class<? extends TK> keyType) {
            this.keyType = keyType;
            return this;
        }

        public Builder<T, TK, TT> dataType(Class<? extends TT> dataType) {
            this.dataType = dataType;
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
            if (targetType == null)
                throw new NullPointerException("targetType");
            if (fn == null)
                throw new NullPointerException("fn");
            if (inputs == null)
                throw new NullPointerException("inputs");
            return new SimpleMakeRule<>(priority, targetType, fn, inputs);
        }

    }

}
