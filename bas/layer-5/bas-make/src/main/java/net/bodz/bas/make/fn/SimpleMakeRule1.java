package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule1<T extends IKeyData<TK, TT>, TK, TT, //
        U extends IKeyData<UK, UT>, UK, UT> //
        implements IMakeRule1<T, TK, TT, U, UK, UT> {

    int priority;
    IMakeable1<TT, UT> fn;
    U input1;

    public SimpleMakeRule1(int priority, @NotNull IMakeable1<TT, UT> fn, @NotNull U input1) {
        this.priority = priority;
        this.fn = fn;
        this.input1 = input1;
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
    public TT make(UT input)
            throws MakeException {
        return fn.make(input);
    }

    public static <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT> //
    Builder<T, TK, TT, U, UK, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT> {

        int priority;
        U input1;
        IMakeable1<TT, UT> fn;

        public Builder<T, TK, TT, U, UK, UT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<T, TK, TT, U, UK, UT> input(@NotNull IKeyData<?, ?>... inputs) {
            input1 = (U) inputs[0];
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT> input1(@NotNull U input1) {
            this.input1 = input1;
            return this;
        }

        public Builder<T, TK, TT, U, UK, UT> fn(@NotNull IMakeable1<TT, UT> fn) {
            this.fn = fn;
            return this;
        }

        public SimpleMakeRule1<T, TK, TT, U, UK, UT> build() {
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1 == null)
                throw new NullPointerException("input1");
            return new SimpleMakeRule1<>(priority, fn, input1);
        }

    }

}
