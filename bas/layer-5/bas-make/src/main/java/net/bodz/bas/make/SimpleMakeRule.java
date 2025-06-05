package net.bodz.bas.make;

import java.util.function.BiConsumer;

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
            @NotNull Class<? extends TK> keyType, //
            @NotNull Class<? extends TT> dataType, //
            @NotNull MakeFunction<T> fn, @NotNull IDataTypedKey<?, ?>... inputs) {
        this.priority = priority;
        this.targetType = targetType;
        this.keyType = keyType;
        this.dataType = dataType;
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

    public static <S, T extends IKeyData<TK, TT>, TK, TT> Builder<S, T, TK, TT> builder() {
        return new Builder<>();
    }

    public static abstract class AbstractBuilder<This, S, T extends IKeyData<TK, TT>, TK, TT> {

        protected int priority;
        protected Class<? extends T> targetType;
        protected Class<? extends TK> keyType;
        protected Class<? extends TT> dataType;
        protected BiConsumer<S, IMakeRule<T, TK, TT>> apply;
        protected S subject;

        @SuppressWarnings("unchecked")
        This _this = (This) this;

        public This priority(int priority) {
            this.priority = priority;
            return _this;
        }

        public This targetType(@NotNull Class<? extends T> targetType) {
            this.targetType = targetType;
            return _this;
        }

        public This keyType(@NotNull Class<? extends TK> keyType) {
            this.keyType = keyType;
            return _this;
        }

        public This dataType(@NotNull Class<? extends TT> dataType) {
            this.dataType = dataType;
            return _this;
        }

        protected void wireUp() {
            if (targetType == null)
                throw new NullPointerException("targetType");
            if (keyType == null)
                keyType = TypeParam.infer1(targetType, IKeyData.class, 0);
            if (dataType == null)
                dataType = TypeParam.infer1(targetType, IKeyData.class, 1);
        }

        public This apply(BiConsumer<S, IMakeRule<T, TK, TT>> apply) {
            this.apply = apply;
            return _this;
        }

        public This subject(@NotNull S subject) {
            this.subject = subject;
            return _this;
        }

//        @SuppressWarnings("unchecked")
//        public This target(@NotNull T target) {
//            this.target = target;
//            if (targetType == null)
//                targetType = (Class<? extends T>) target.getClass();
//            return _this;
//        }

//        public This target(@NotNull T target, @NotNull Class<? extends T> targetType) {
//            this.target = target;
//            this.targetType = targetType;
//            return _this;
//        }

    }

    public static class Builder<S, T extends IKeyData<TK, TT>, TK, TT>
            extends AbstractBuilder<Builder<S, T, TK, TT>, S, T, TK, TT> {

        MakeFunction<T> fn;
        IKeyData<?, ?>[] inputs = {};

        public Builder<S, T, TK, TT> fn(@NotNull MakeFunction<T> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<S, T, TK, TT> input(@NotNull IKeyData<?, ?>... inputs) {
            this.inputs = inputs;
            return this;
        }

        public SimpleMakeRule<T, TK, TT> build() {
            wireUp();
            if (fn == null)
                throw new NullPointerException("fn");
            if (inputs == null)
                throw new NullPointerException("inputs");
            return new SimpleMakeRule<>(priority, targetType, keyType, dataType, fn, inputs);
        }

        public void make(@NotNull MakeFunction<T> fn) {
            make(subject, fn);
        }

        public void make(@NotNull S subject, @NotNull MakeFunction<T> fn) {
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

    public static class Dispatcher<S, T extends IKeyData<TK, TT>, TK, TT> {

        BiConsumer<S, IMakeRule<T, TK, TT>> apply;
        S subject;

        public Dispatcher(BiConsumer<S, IMakeRule<T, TK, TT>> apply, S subject) {
            this.apply = apply;
            this.subject = subject;
        }

        public SimpleMakeRule.Builder<S, T, TK, TT> input(@NotNull IKeyData<?, ?>... inputs) {
            return SimpleMakeRule.<S, T, TK, TT>builder().apply(apply).subject(subject).input(inputs);
        }

//        public <U extends IKeyData<UK, UT>, UK, UT> //
//        SimpleMakeRule1.Builder<S, T, TK, TT, U, UK, UT> input(U input1) {
//            return SimpleMakeRule1.<S, T, TK, TT, U, UK, UT>builder().apply(apply).subject(subject)//
//                    .input(input1);
//        }

    }

}
