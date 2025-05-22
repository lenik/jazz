package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class SimplePatternMakeRule2<Tp extends IKeyPattern<Param, K>, Param, K, //
        Us extends IParameterizedKeys<Param, UK>, UK, Vs extends IParameterizedKeys<Param, VK>, VK, //
        T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT, V extends IKeyData<VK, VT>, VT>
        implements IPatternMakeRule2<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

    int priority;
    Tp pattern;
    Us input1s;
    Vs input2s;
    CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn;

    public SimplePatternMakeRule2(int priority, @NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
        this.priority = priority;
        this.pattern = pattern;
        this.input1s = input1s;
        this.input2s = input2s;
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
    public Vs getInput2() {
        return input2s;
    }

    @Override
    public MakeFunction2<TT, UT, VT> compile(@NotNull T target, U input1, V input2)
            throws MakeException {
        return fn.compile(target, input1, input2);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT, V extends IKeyData<VK, VT>, VT> //
    Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT, V extends IKeyData<VK, VT>, VT> {

        int priority;
        Tp pattern;
        Us input1s;
        Vs input2s;
        CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn;

        public Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> pattern(@NotNull Tp pattern) {
            this.pattern = pattern;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> input(@NotNull IParameterizedKeys<?, ?>... inputss) {
            this.input1s = (Us) inputss[0];
            return this;
        }

        public Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> input1(@NotNull Us input1s) {
            this.input1s = input1s;
            return this;
        }

        public Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> input2(@NotNull Vs input2s) {
            this.input2s = input2s;
            return this;
        }

        public Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> fn(@NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
            this.fn = fn;
            return this;
        }

        public SimplePatternMakeRule2<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimplePatternMakeRule2<>(priority, pattern, input1s, input2s, fn);
        }

    }

}
