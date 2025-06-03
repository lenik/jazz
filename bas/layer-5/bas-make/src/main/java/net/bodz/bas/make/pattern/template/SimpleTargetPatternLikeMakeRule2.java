package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.fn.IMakeable2;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleTargetPatternLikeMakeRule2<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
        Keys extends IParameterizedTarget<?, ?, ?, ?>, //
        Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
        Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT> //
        implements ITargetPatternLikeMakeRule2<Tp, Param, TK, Keys, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

    int priority;
    Tp pattern;
    CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn;
    Us input1s;
    Vs input2s;

    public SimpleTargetPatternLikeMakeRule2(int priority, @NotNull Tp pattern, @NotNull CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn, @NotNull Us input1s, @NotNull Vs input2s) {
        this.priority = priority;
        this.pattern = pattern;
        this.fn = fn;
        this.input1s = input1s;
        this.input2s = input2s;
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

    public static abstract class Builder<self_t, Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> {

        protected int priority;
        protected Tp pattern;
        protected CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn;
        protected Us input1s;
        protected Vs input2s;

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

        public self_t fn(@NotNull CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn) {
            this.fn = fn;
            return _this;
        }

        @SuppressWarnings("unchecked")
        public self_t input(@NotNull IParameterizedTarget<?, ?, ?, ?>... inputss) {
            this.input1s = (Us) inputss[0];
            this.input2s = (Vs) inputss[1];
            return _this;
        }

        public self_t input1(@NotNull Us input1s) {
            this.input1s = input1s;
            return _this;
        }

        public self_t input2(@NotNull Vs input2s) {
            this.input2s = input2s;
            return _this;
        }

    }

}
