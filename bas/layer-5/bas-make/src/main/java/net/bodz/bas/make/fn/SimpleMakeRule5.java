package net.bodz.bas.make.fn;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule5<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT, //
        Y extends IKeyData<YK, YT>, YK, YT> //
        implements IMakeRule5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    IMakeable5<TT, UT, VT, WT, XT, YT> fn;
    IDataTypedKey<UK, UT> input1;
    IDataTypedKey<VK, VT> input2;
    IDataTypedKey<WK, WT> input3;
    IDataTypedKey<XK, XT> input4;
    IDataTypedKey<YK, YT> input5;

    public SimpleMakeRule5(int priority, @NotNull Class<? extends T> targetType, @NotNull Class<? extends TK> keyType, @NotNull Class<? extends TT> dataType, @NotNull IMakeable5<TT, UT, VT, WT, XT, YT> fn, @NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2, @NotNull IDataTypedKey<WK, WT> input3, @NotNull IDataTypedKey<XK, XT> input4, @NotNull IDataTypedKey<YK, YT> input5) {
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
    public IDataTypedKey<YK, YT> getInput5() {
        return input5;
    }

    @Override
    public TT make(UT input1, VT input2, WT input3, XT input4, YT input5)
            throws MakeException {
        return fn.make(input1, input2, input3, input4, input5);
    }

    public static <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT> //
    Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT> {

        int priority;
        Class<? extends T> targetType;
        Class<? extends TK> keyType;
        Class<? extends TT> dataType;
        IMakeable5<TT, UT, VT, WT, XT, YT> fn;
        IDataTypedKey<UK, UT> input1;
        IDataTypedKey<VK, VT> input2;
        IDataTypedKey<WK, WT> input3;
        IDataTypedKey<XK, XT> input4;
        IDataTypedKey<YK, YT> input5;

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> targetType(Class<? extends T> targetType) {
            this.targetType = targetType;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn(@NotNull IMakeable5<TT, UT, VT, WT, XT, YT> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input(@NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2, @NotNull IDataTypedKey<WK, WT> input3, @NotNull IDataTypedKey<XK, XT> input4, @NotNull IDataTypedKey<YK, YT> input5) {
            this.input1 = input1;
            this.input2 = input2;
            this.input3 = input3;
            this.input4 = input4;
            this.input5 = input5;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input1(@NotNull IDataTypedKey<UK, UT> input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input2(@NotNull IDataTypedKey<VK, VT> input2) {
            this.input2 = input2;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input3(@NotNull IDataTypedKey<WK, WT> input3) {
            this.input3 = input3;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input4(@NotNull IDataTypedKey<XK, XT> input4) {
            this.input4 = input4;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input5(@NotNull IDataTypedKey<YK, YT> input5) {
            this.input5 = input5;
            return this;
        }

        public SimpleMakeRule5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> build() {
            if (targetType == null)
                throw new NullPointerException("targetType");
            if (keyType == null)
                keyType = TypeParam.infer1(targetType, IKeyData.class, 0);
            if (dataType == null)
                dataType = TypeParam.infer1(targetType, IKeyData.class, 1);
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
            return new SimpleMakeRule5<>(priority, targetType, keyType, dataType, fn, input1, input2, input3, input4, input5);
        }

    }

}
