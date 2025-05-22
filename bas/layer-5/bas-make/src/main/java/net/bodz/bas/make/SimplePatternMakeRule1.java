package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class SimplePatternMakeRule1<Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKeys<Param, UK>, UK, //
        T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT>
        implements IPatternMakeRule1<Tp, Param, K, Us, UK, T, TT, U, UT> {

    int priority;
    Tp pattern;
    Us input1s;
    CompileFunction1<T, K, TT, U, UK, UT> fn;

    public SimplePatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
        this.priority = priority;
        this.pattern = pattern;
        this.input1s = input1s;
        this.fn = fn;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Tp getPattern() {
        return pattern;
    }

    @Override
    public Us getInput1() {
        return input1s;
    }

    @Override
    public MakeFunction1<TT, UT> compile(@NotNull T target, U input1)
            throws MakeException {
        return fn.compile(target, input1);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKeys<Param, UK>, UK, //
            T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT> //
    Builder<Tp, Param, K, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKeys<Param, UK>, UK, //
            T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT> {

        int priority;
        Tp pattern;
        Us input1s;
        CompileFunction1<T, K, TT, U, UK, UT> fn;

        public Builder<Tp, Param, K, Us, UK, T, TT, U, UT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<Tp, Param, K, Us, UK, T, TT, U, UT> pattern(@NotNull Tp pattern) {
            this.pattern = pattern;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<Tp, Param, K, Us, UK, T, TT, U, UT> input(@NotNull IParameterizedKeys<?, ?>... inputss) {
            this.input1s = (Us) inputss[0];
            return this;
        }

        public Builder<Tp, Param, K, Us, UK, T, TT, U, UT> input1(@NotNull Us input1s) {
            this.input1s = input1s;
            return this;
        }

        public Builder<Tp, Param, K, Us, UK, T, TT, U, UT> fn(@NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
            this.fn = fn;
            return this;
        }

        public SimplePatternMakeRule1<Tp, Param, K, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimplePatternMakeRule1<>(priority, pattern, input1s, fn);
        }

    }

}
