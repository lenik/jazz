package net.bodz.bas.make.fn;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule6<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT, //
        Y extends IKeyData<YK, YT>, YK, YT, //
        Z extends IKeyData<ZK, ZT>, ZK, ZT> //
        implements IMakeRule6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn;
    IDataTypedKey<UK, UT> input1;
    IDataTypedKey<VK, VT> input2;
    IDataTypedKey<WK, WT> input3;
    IDataTypedKey<XK, XT> input4;
    IDataTypedKey<YK, YT> input5;
    IDataTypedKey<ZK, ZT> input6;

    public SimpleMakeRule6(int priority, @NotNull Class<? extends T> targetType, @NotNull Class<? extends TK> keyType, @NotNull Class<? extends TT> dataType, @NotNull IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn, @NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2, @NotNull IDataTypedKey<WK, WT> input3, @NotNull IDataTypedKey<XK, XT> input4, @NotNull IDataTypedKey<YK, YT> input5, @NotNull IDataTypedKey<ZK, ZT> input6) {
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
    public IDataTypedKey<ZK, ZT> getInput6() {
        return input6;
    }

    @Override
    public TT make(UT input1, VT input2, WT input3, XT input4, YT input5, ZT input6)
            throws MakeException {
        return fn.make(input1, input2, input3, input4, input5, input6);
    }

    public static <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT> //
    Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT> {

        int priority;
        Class<? extends T> targetType;
        Class<? extends TK> keyType;
        Class<? extends TT> dataType;
        IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn;
        IDataTypedKey<UK, UT> input1;
        IDataTypedKey<VK, VT> input2;
        IDataTypedKey<WK, WT> input3;
        IDataTypedKey<XK, XT> input4;
        IDataTypedKey<YK, YT> input5;
        IDataTypedKey<ZK, ZT> input6;

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> targetType(Class<? extends T> targetType) {
            this.targetType = targetType;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn(@NotNull IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input(@NotNull IDataTypedKey<UK, UT> input1, @NotNull IDataTypedKey<VK, VT> input2, @NotNull IDataTypedKey<WK, WT> input3, @NotNull IDataTypedKey<XK, XT> input4, @NotNull IDataTypedKey<YK, YT> input5, @NotNull IDataTypedKey<ZK, ZT> input6) {
            this.input1 = input1;
            this.input2 = input2;
            this.input3 = input3;
            this.input4 = input4;
            this.input5 = input5;
            this.input6 = input6;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input1(@NotNull IDataTypedKey<UK, UT> input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input2(@NotNull IDataTypedKey<VK, VT> input2) {
            this.input2 = input2;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input3(@NotNull IDataTypedKey<WK, WT> input3) {
            this.input3 = input3;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input4(@NotNull IDataTypedKey<XK, XT> input4) {
            this.input4 = input4;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input5(@NotNull IDataTypedKey<YK, YT> input5) {
            this.input5 = input5;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input6(@NotNull IDataTypedKey<ZK, ZT> input6) {
            this.input6 = input6;
            return this;
        }

        public SimpleMakeRule6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> build() {
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
            if (input6 == null)
                throw new NullPointerException("input6");
            return new SimpleMakeRule6<>(priority, targetType, keyType, dataType, fn, input1, input2, input3, input4, input5, input6);
        }

    }

}
