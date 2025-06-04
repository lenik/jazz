package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.make.SimpleMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule2<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT> //
        implements IMakeRule2<T, TK, TT, U, UK, UT, V, VK, VT> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    IMakeable2<TT, UT, VT> fn;
    IDataTypedKey<UK, UT> input1;
    IDataTypedKey<VK, VT> input2;

    public SimpleMakeRule2(int priority, @NotNull Class<? extends T> targetType, @NotNull Class<? extends TK> keyType, @NotNull Class<? extends TT> dataType, @NotNull IMakeable2<TT, UT, VT> fn, @NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2) {
        this.priority = priority;
        this.targetType = targetType;
        this.keyType = keyType;
        this.dataType = dataType;
        this.fn = fn;
        this.input1 = input1;
        this.input2 = input2;
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
    public IDataTypedKey<UK, UT> getInput1() {
        return input1;
    }

    @Override
    public IDataTypedKey<VK, VT> getInput2() {
        return input2;
    }

    @Override
    public TT make(UT input1, VT input2)
            throws MakeException {
        return fn.make(input1, input2);
    }

    public static <S, T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
    Builder<S, T, TK, TT, U, UK, UT, V, VK, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
            extends SimpleMakeRule.AbstractBuilder<Builder<S, T, TK, TT, U, UK, UT, V, VK, VT>, S, T, TK, TT> { 

        IMakeable2<TT, UT, VT> fn;
        IDataTypedKey<UK, UT> input1;
        IDataTypedKey<VK, VT> input2;

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT> fn(@NotNull IMakeable2<TT, UT, VT> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT> input(@NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2) {
            this.input1 = input1;
            this.input2 = input2;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT> input1(@NotNull IDataTypedKey<UK, UT> input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT> input2(@NotNull IDataTypedKey<VK, VT> input2) {
            this.input2 = input2;
            return this;
        }

        public SimpleMakeRule2<T, TK, TT, U, UK, UT, V, VK, VT> build() {
            wireUp();
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1 == null)
                throw new NullPointerException("input1");
            if (input2 == null)
                throw new NullPointerException("input2");
            return new SimpleMakeRule2<>(priority, targetType, keyType, dataType, fn, input1, input2);
        }

        public void make(@NotNull IMakeable2<TT, UT, VT> fn) {
            make(subject, fn);
        }

        public void make(@NotNull S subject, @NotNull IMakeable2<TT, UT, VT> fn) {
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
