package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.fn.IMakeable1;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleKeyPatternLikeMakeRule1<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKey<?, ?>, //
        Us extends IParameterizedKey<Param, UK>, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT> //
        implements IKeyPatternLikeMakeRule1<Tp, Param, K, Keys, Us, T, TT, U, UK, UT> {

    int priority;
    Tp pattern;
    CompileFunction1<T, K, TT, U, UK, UT> fn;
    Us input1s;

    public SimpleKeyPatternLikeMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn, @NotNull Us input1s) {
        this.priority = priority;
        this.pattern = pattern;
        this.fn = fn;
        this.input1s = input1s;
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

    public static abstract class Builder<self_t, Tp extends IKeyPatternLike<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT> {

        protected int priority;
        protected Tp pattern;
        protected CompileFunction1<T, K, TT, U, UK, UT> fn;
        protected Us input1s;

        @SuppressWarnings("unchecked")
        protected final self_t _this = (self_t) this;

        public self_t priority(int priority) {
            this.priority = priority;
            return _this;
        }

        public self_t pattern(@NotNull Tp pattern) {
            this.pattern = pattern;
            return _this;
        }

        public self_t fn(@NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
            this.fn = fn;
            return _this;
        }

        @SuppressWarnings("unchecked")
        public self_t input(@NotNull IParameterizedKey<?, ?>... inputss) {
            this.input1s = (Us) inputss[0];
            return _this;
        }

        public self_t input1(@NotNull Us input1s) {
            this.input1s = input1s;
            return _this;
        }

    }

}
