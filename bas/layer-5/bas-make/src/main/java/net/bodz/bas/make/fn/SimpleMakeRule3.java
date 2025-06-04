package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.make.SimpleMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule3<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT> //
        implements IMakeRule3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    IMakeable3<TT, UT, VT, WT> fn;
    IDataTypedKey<UK, UT> input1;
    IDataTypedKey<VK, VT> input2;
    IDataTypedKey<WK, WT> input3;

    public SimpleMakeRule3(int priority, @NotNull Class<? extends T> targetType, @NotNull Class<? extends TK> keyType, @NotNull Class<? extends TT> dataType, @NotNull IMakeable3<TT, UT, VT, WT> fn, @NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2, @NotNull IDataTypedKey<WK, WT> input3) {
        this.priority = priority;
        this.targetType = targetType;
        this.keyType = keyType;
        this.dataType = dataType;
        this.fn = fn;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
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
    public IDataTypedKey<WK, WT> getInput3() {
        return input3;
    }

    @Override
    public TT make(UT input1, VT input2, WT input3)
            throws MakeException {
        return fn.make(input1, input2, input3);
    }

    public static <S, T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> //
    Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> //
            extends SimpleMakeRule.AbstractBuilder<Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>, S, T, TK, TT> { 

        IMakeable3<TT, UT, VT, WT> fn;
        IDataTypedKey<UK, UT> input1;
        IDataTypedKey<VK, VT> input2;
        IDataTypedKey<WK, WT> input3;

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn(@NotNull IMakeable3<TT, UT, VT, WT> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input(@NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2, @NotNull IDataTypedKey<WK, WT> input3) {
            this.input1 = input1;
            this.input2 = input2;
            this.input3 = input3;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input1(@NotNull IDataTypedKey<UK, UT> input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input2(@NotNull IDataTypedKey<VK, VT> input2) {
            this.input2 = input2;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input3(@NotNull IDataTypedKey<WK, WT> input3) {
            this.input3 = input3;
            return this;
        }

        public SimpleMakeRule3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> build() {
            wireUp();
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1 == null)
                throw new NullPointerException("input1");
            if (input2 == null)
                throw new NullPointerException("input2");
            if (input3 == null)
                throw new NullPointerException("input3");
            return new SimpleMakeRule3<>(priority, targetType, keyType, dataType, fn, input1, input2, input3);
        }

        public void make(@NotNull IMakeable3<TT, UT, VT, WT> fn) {
            make(subject, fn);
        }

        public void make(@NotNull S subject, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
