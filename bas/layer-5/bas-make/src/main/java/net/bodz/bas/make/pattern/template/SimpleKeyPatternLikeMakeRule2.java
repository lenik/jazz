package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.fn.IMakeable2;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleKeyPatternLikeMakeRule2<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT>
        implements IKeyPatternLikeMakeRule2<Tp, Param, K, Keys, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

    int priority;
    Tp pattern;
    Us input1s;
    Vs input2s;
    CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn;

    public SimpleKeyPatternLikeMakeRule2(int priority, @NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
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
    public IMakeable2<TT, UT, VT> compile(@NotNull T target, U input1, V input2)
            throws CompileException {
        return fn.compile(target, input1, input2);
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<self_t, Tp extends IKeyPatternLike<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> {

        protected int priority;
        protected Tp pattern;
        protected Us input1s;
        protected Vs input2s;
        protected CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn;

        public self_t priority(int priority) {
            this.priority = priority;
            return (self_t) this;
        }

        public self_t pattern(@NotNull Tp pattern) {
            this.pattern = pattern;
            return (self_t) this;
        }

        @SuppressWarnings("unchecked")
        public self_t input(@NotNull IParameterizedKeys<?, ?>... inputss) {
            this.input1s = (Us) inputss[0];
            return (self_t) this;
        }

        public self_t input1(@NotNull Us input1s) {
            this.input1s = input1s;
            return (self_t) this;
        }

        public self_t input2(@NotNull Vs input2s) {
            this.input2s = input2s;
            return (self_t) this;
        }

        public self_t fn(@NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
            this.fn = fn;
            return (self_t) this;
        }

    }

}
