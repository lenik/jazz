package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule2<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT>
        implements IMakeRule2<T, TK, TT, U, UK, UT, V, VK, VT> {

    int priority;
    IMakeable2<TT, UT, VT> fn;
    U input1;
    V input2;

    public SimpleMakeRule2(int priority, @NotNull IMakeable2<TT, UT, VT> fn, @NotNull U input1, @NotNull V input2) {
        this.priority = priority;
        this.input1 = input1;
        this.input2 = input2;
        this.fn = fn;
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
    public TT make(UT in1, VT in2)
            throws MakeException {
        return fn.make(in1, in2);
    }

    public static <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
    Builder<T, TK, TT, U, UK, UT, V, VK, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> {

        int priority;
        U input1;
        V input2;
        IMakeable2<TT, UT, VT> fn;

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<T, TK, TT, U, UK, UT, V, VK, VT> input(@NotNull IKeyData<?, ?>... inputs) {
            input1 = (U) inputs[0];
            input2 = (V) inputs[1];
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT> input1(@NotNull U input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT> input2(@NotNull V input2) {
            this.input2 = input2;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT> fn(@NotNull IMakeable2<TT, UT, VT> fn) {
            this.fn = fn;
            return this;
        }

        public SimpleMakeRule2<T, TK, TT, U, UK, UT, V, VK, VT> build() {
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1 == null)
                throw new NullPointerException("input1");
            if (input2 == null)
                throw new NullPointerException("input2");
            return new SimpleMakeRule2<>(priority, fn, input1, input2);
        }

    }

}
