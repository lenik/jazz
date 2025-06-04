package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.make.SimpleMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule1<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT> //
        implements IMakeRule1<T, TK, TT, U, UK, UT> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    IMakeable1<TT, UT> fn;
    IDataTypedKey<UK, UT> input1;

    public SimpleMakeRule1(int priority, @NotNull Class<? extends T> targetType, @NotNull Class<? extends TK> keyType, @NotNull Class<? extends TT> dataType, @NotNull IMakeable1<TT, UT> fn, @NotNull IDataTypedKey<UK, UT> input1) {
        this.priority = priority;
        this.targetType = targetType;
        this.keyType = keyType;
        this.dataType = dataType;
        this.fn = fn;
        this.input1 = input1;
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
    public TT make(UT input1)
            throws MakeException {
        return fn.make(input1);
    }

    public static <S, T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT> //
    Builder<S, T, TK, TT, U, UK, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT> //
            extends SimpleMakeRule.AbstractBuilder<Builder<S, T, TK, TT, U, UK, UT>, S, T, TK, TT> { 

        IMakeable1<TT, UT> fn;
        IDataTypedKey<UK, UT> input1;

        public Builder<S, T, TK, TT, U, UK, UT> fn(@NotNull IMakeable1<TT, UT> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT> input(@NotNull IDataTypedKey<UK, UT> input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT> input1(@NotNull IDataTypedKey<UK, UT> input1) {
            this.input1 = input1;
            return this;
        }

        public SimpleMakeRule1<T, TK, TT, U, UK, UT> build() {
            wireUp();
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1 == null)
                throw new NullPointerException("input1");
            return new SimpleMakeRule1<>(priority, targetType, keyType, dataType, fn, input1);
        }

        public void make(@NotNull IMakeable1<TT, UT> fn) {
            make(subject, fn);
        }

        public void make(@NotNull S subject, @NotNull IMakeable1<TT, UT> fn) {
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
