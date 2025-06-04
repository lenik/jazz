package net.bodz.bas.make.fn;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.make.SimpleMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule4<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT> //
        implements IMakeRule4<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    IMakeable4<TT, UT, VT, WT, XT> fn;
    IDataTypedKey<UK, UT> input1;
    IDataTypedKey<VK, VT> input2;
    IDataTypedKey<WK, WT> input3;
    IDataTypedKey<XK, XT> input4;

    public SimpleMakeRule4(int priority, @NotNull Class<? extends T> targetType, @NotNull Class<? extends TK> keyType, @NotNull Class<? extends TT> dataType, @NotNull IMakeable4<TT, UT, VT, WT, XT> fn, @NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2, @NotNull IDataTypedKey<WK, WT> input3, @NotNull IDataTypedKey<XK, XT> input4) {
        this.priority = priority;
        this.targetType = targetType;
        this.keyType = keyType;
        this.dataType = dataType;
        this.fn = fn;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.input4 = input4;
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
    public IDataTypedKey<XK, XT> getInput4() {
        return input4;
    }

    @Override
    public TT make(UT input1, VT input2, WT input3, XT input4)
            throws MakeException {
        return fn.make(input1, input2, input3, input4);
    }

    public static <S, T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT> //
    Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT> //
            extends SimpleMakeRule.AbstractBuilder<Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>, S, T, TK, TT> { 

        IMakeable4<TT, UT, VT, WT, XT> fn;
        IDataTypedKey<UK, UT> input1;
        IDataTypedKey<VK, VT> input2;
        IDataTypedKey<WK, WT> input3;
        IDataTypedKey<XK, XT> input4;

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn(@NotNull IMakeable4<TT, UT, VT, WT, XT> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> input(@NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2, @NotNull IDataTypedKey<WK, WT> input3, @NotNull IDataTypedKey<XK, XT> input4) {
            this.input1 = input1;
            this.input2 = input2;
            this.input3 = input3;
            this.input4 = input4;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> input1(@NotNull IDataTypedKey<UK, UT> input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> input2(@NotNull IDataTypedKey<VK, VT> input2) {
            this.input2 = input2;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> input3(@NotNull IDataTypedKey<WK, WT> input3) {
            this.input3 = input3;
            return this;
        }

        public Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> input4(@NotNull IDataTypedKey<XK, XT> input4) {
            this.input4 = input4;
            return this;
        }

        public SimpleMakeRule4<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> build() {
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
            return new SimpleMakeRule4<>(priority, targetType, keyType, dataType, fn, input1, input2, input3, input4);
        }

        public void make(@NotNull IMakeable4<TT, UT, VT, WT, XT> fn) {
            make(subject, fn);
        }

        public void make(@NotNull S subject, @NotNull IMakeable4<TT, UT, VT, WT, XT> fn) {
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
