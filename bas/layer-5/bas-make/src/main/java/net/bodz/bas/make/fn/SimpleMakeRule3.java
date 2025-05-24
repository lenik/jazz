package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule3<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT>
        implements IMakeRule3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> {

    int priority;
    IMakeable3<TT, UT, VT, WT> fn;
    U input1;
    V input2;
    W input3;

    public SimpleMakeRule3(int priority, @NotNull IMakeable3<TT, UT, VT, WT> fn, @NotNull U input1, @NotNull V input2, @NotNull W input3) {
        this.priority = priority;
        this.fn = fn;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public U getInput1() {
        return input1;
    }

    @Override
    public V getInput2() {
        return input2;
    }

    @Override
    public W getInput3() {
        return input3;
    }

    @Override
    public TT make(UT in1, VT in2, WT in3)
            throws MakeException {
        return fn.make(in1, in2, in3);
    }

    public static <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> //
    Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> {

        int priority;
        U input1;
        V input2;
        W input3;
        IMakeable3<TT, UT, VT, WT> fn;

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input(@NotNull IKeyData<?, ?>... inputs) {
            input1 = (U) inputs[0];
            input2 = (V) inputs[1];
            input3 = (W) inputs[2];
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input1(@NotNull U input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input2(@NotNull V input2) {
            this.input2 = input2;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input3(@NotNull W input3) {
            this.input3 = input3;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn(@NotNull IMakeable3<TT, UT, VT, WT> fn) {
            this.fn = fn;
            return this;
        }

        public SimpleMakeRule3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> build() {
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1 == null)
                throw new NullPointerException("input1");
            if (input2 == null)
                throw new NullPointerException("input2");
            if (input3 == null)
                throw new NullPointerException("input3");
            return new SimpleMakeRule3<>(priority, fn, input1, input2, input3);
        }

    }

}
