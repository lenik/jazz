package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.make.SimpleMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule7<T extends IKeyData<TK, TT>, TK, TT, //
        U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
        implements IMakeRule7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn;
    IDataTypedKey<U1K, U1T> input1;
    IDataTypedKey<U2K, U2T> input2;
    IDataTypedKey<U3K, U3T> input3;
    IDataTypedKey<U4K, U4T> input4;
    IDataTypedKey<U5K, U5T> input5;
    IDataTypedKey<U6K, U6T> input6;
    IDataTypedKey<U7K, U7T> input7;

    public SimpleMakeRule7(int priority, @NotNull Class<? extends T> targetType, @NotNull Class<? extends TK> keyType, @NotNull Class<? extends TT> dataType, @NotNull IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn, @NotNull IDataTypedKey<U1K, U1T> input1, @NotNull IDataTypedKey<U2K, U2T> input2, @NotNull IDataTypedKey<U3K, U3T> input3, @NotNull IDataTypedKey<U4K, U4T> input4, @NotNull IDataTypedKey<U5K, U5T> input5, @NotNull IDataTypedKey<U6K, U6T> input6, @NotNull IDataTypedKey<U7K, U7T> input7) {
        this.priority = priority;
        this.targetType = targetType;
        this.keyType = keyType;
        this.dataType = dataType;
        this.fn = fn;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.input4 = input4;
        this.input5 = input5;
        this.input6 = input6;
        this.input7 = input7;
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
    public IDataTypedKey<U1K, U1T> getInput1() {
        return input1;
    }

    @Override
    public IDataTypedKey<U2K, U2T> getInput2() {
        return input2;
    }

    @Override
    public IDataTypedKey<U3K, U3T> getInput3() {
        return input3;
    }

    @Override
    public IDataTypedKey<U4K, U4T> getInput4() {
        return input4;
    }

    @Override
    public IDataTypedKey<U5K, U5T> getInput5() {
        return input5;
    }

    @Override
    public IDataTypedKey<U6K, U6T> getInput6() {
        return input6;
    }

    @Override
    public IDataTypedKey<U7K, U7T> getInput7() {
        return input7;
    }

    @Override
    public TT make(U1T input1, U2T input2, U3T input3, U4T input4, U5T input5, U6T input6, U7T input7)
            throws MakeException {
        return fn.make(input1, input2, input3, input4, input5, input6, input7);
    }

    public static <S, T extends IKeyData<TK, TT>, TK, TT, //
            U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
    Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> builder() {
        return new Builder<>();
    }

    public static class Builder<S, T extends IKeyData<TK, TT>, TK, TT, //
            U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
            extends SimpleMakeRule.AbstractBuilder<Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>, S, T, TK, TT> { 

        IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn;
        IDataTypedKey<U1K, U1T> input1;
        IDataTypedKey<U2K, U2T> input2;
        IDataTypedKey<U3K, U3T> input3;
        IDataTypedKey<U4K, U4T> input4;
        IDataTypedKey<U5K, U5T> input5;
        IDataTypedKey<U6K, U6T> input6;
        IDataTypedKey<U7K, U7T> input7;

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn(@NotNull IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input(@NotNull IDataTypedKey<U1K, U1T> input1, @NotNull IDataTypedKey<U2K, U2T> input2, @NotNull IDataTypedKey<U3K, U3T> input3, @NotNull IDataTypedKey<U4K, U4T> input4, @NotNull IDataTypedKey<U5K, U5T> input5, @NotNull IDataTypedKey<U6K, U6T> input6, @NotNull IDataTypedKey<U7K, U7T> input7) {
            this.input1 = input1;
            this.input2 = input2;
            this.input3 = input3;
            this.input4 = input4;
            this.input5 = input5;
            this.input6 = input6;
            this.input7 = input7;
            return this;
        }

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input1(@NotNull IDataTypedKey<U1K, U1T> input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input2(@NotNull IDataTypedKey<U2K, U2T> input2) {
            this.input2 = input2;
            return this;
        }

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input3(@NotNull IDataTypedKey<U3K, U3T> input3) {
            this.input3 = input3;
            return this;
        }

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input4(@NotNull IDataTypedKey<U4K, U4T> input4) {
            this.input4 = input4;
            return this;
        }

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input5(@NotNull IDataTypedKey<U5K, U5T> input5) {
            this.input5 = input5;
            return this;
        }

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input6(@NotNull IDataTypedKey<U6K, U6T> input6) {
            this.input6 = input6;
            return this;
        }

        public Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input7(@NotNull IDataTypedKey<U7K, U7T> input7) {
            this.input7 = input7;
            return this;
        }

        public SimpleMakeRule7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> build() {
            wireUp();
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1 == null)
                throw new NullPointerException("input1");
            if (input2 == null)
                throw new NullPointerException("input2");
            if (input3 == null)
                throw new NullPointerException("input3");
            if (input4 == null)
                throw new NullPointerException("input4");
            if (input5 == null)
                throw new NullPointerException("input5");
            if (input6 == null)
                throw new NullPointerException("input6");
            if (input7 == null)
                throw new NullPointerException("input7");
            return new SimpleMakeRule7<>(priority, targetType, keyType, dataType, fn, input1, input2, input3, input4, input5, input6, input7);
        }

        public void make(@NotNull IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn) {
            make(subject, fn);
        }

        public void make(@NotNull S subject, @NotNull IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn) {
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
