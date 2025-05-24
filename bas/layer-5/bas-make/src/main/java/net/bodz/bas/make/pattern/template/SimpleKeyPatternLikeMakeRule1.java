package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.fn.IMakeable1;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleKeyPatternLikeMakeRule1<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT>
        implements IKeyPatternLikeMakeRule1<Tp, Param, K, Keys, Us, UK, T, TT, U, UT> {

    int priority;
    Tp pattern;
    Us input1s;
    CompileFunction1<T, K, TT, U, UK, UT> fn;

    public SimpleKeyPatternLikeMakeRule1(int priority, @NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
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
    public IMakeable1<TT, UT> compile(@NotNull T target, U input1)
            throws CompileException {
        return fn.compile(target, input1);
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<self_t, Tp extends IKeyPatternLike<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> {

        protected int priority;
        protected Tp pattern;
        protected Us input1s;
        protected CompileFunction1<T, K, TT, U, UK, UT> fn;

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

        public self_t fn(@NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
            this.fn = fn;
            return (self_t) this;
        }

    }

}
