package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule2<T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT, V extends IDataEntry<VK, VT>, VK, VT>
        implements IMakeRule2<T, TK, TT, U, UK, UT, V, VK, VT> {

    int priority;
    U input1;
    V input2;
    MakeFunction2<TT, UT, VT> fn;

    public SimpleMakeRule2(int priority, @NotNull U input1, @NotNull V input2, @NotNull MakeFunction2<TT, UT, VT> fn) {
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

    public static <T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT, V extends IDataEntry<VK, VT>, VK, VT> //
    Builder<T, TK, TT, U, UK, UT, V, VK, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT, V extends IDataEntry<VK, VT>, VK, VT> {

        int priority;
        U input1;
        V input2;
        MakeFunction2<TT, UT, VT> fn;

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<T, TK, TT, U, UK, UT, V, VK, VT> input(@NotNull IDataEntry<?, ?>... inputs) {
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

        public Builder<T, TK, TT, U, UK, UT, V, VK, VT> fn(@NotNull MakeFunction2<TT, UT, VT> fn) {
            this.fn = fn;
            return this;
        }

        public SimpleMakeRule2<T, TK, TT, U, UK, UT, V, VK, VT> build() {
            if (input1 == null)
                throw new NullPointerException("input1");
            if (input2 == null)
                throw new NullPointerException("input2");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleMakeRule2<>(priority, input1, input2, fn);
        }

    }

}
