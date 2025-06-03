package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.CompileFunction7;
import net.bodz.bas.make.fn.IMakeable7;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleTargetPatternLikeMakeRule7<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
        Keys extends IParameterizedTarget<?, ?, ?, ?>, //
        U1s extends IParameterizedTarget<Param, U1, U1K, U1T>, U1K, //
        U2s extends IParameterizedTarget<Param, U2, U2K, U2T>, U2K, //
        U3s extends IParameterizedTarget<Param, U3, U3K, U3T>, U3K, //
        U4s extends IParameterizedTarget<Param, U4, U4K, U4T>, U4K, //
        U5s extends IParameterizedTarget<Param, U5, U5K, U5T>, U5K, //
        U6s extends IParameterizedTarget<Param, U6, U6K, U6T>, U6K, //
        U7s extends IParameterizedTarget<Param, U7, U7K, U7T>, U7K, //
        T extends IKeyData<TK, TT>, TT, //
        U1 extends IKeyData<U1K, U1T>, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7T> //
        implements ITargetPatternLikeMakeRule7<Tp, Param, TK, Keys, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T> {

    int priority;
    Tp pattern;
    CompileFunction7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn;
    U1s input1s;
    U2s input2s;
    U3s input3s;
    U4s input4s;
    U5s input5s;
    U6s input6s;
    U7s input7s;

    public SimpleTargetPatternLikeMakeRule7(int priority, @NotNull Tp pattern, @NotNull CompileFunction7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn, @NotNull U1s input1s, @NotNull U2s input2s, @NotNull U3s input3s, @NotNull U4s input4s, @NotNull U5s input5s, @NotNull U6s input6s, @NotNull U7s input7s) {
        this.priority = priority;
        this.pattern = pattern;
        this.fn = fn;
        this.input1s = input1s;
        this.input2s = input2s;
        this.input3s = input3s;
        this.input4s = input4s;
        this.input5s = input5s;
        this.input6s = input6s;
        this.input7s = input7s;
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
    public U1s getInput1() {
        return input1s;
    }

    @Override
    public U2s getInput2() {
        return input2s;
    }

    @Override
    public U3s getInput3() {
        return input3s;
    }

    @Override
    public U4s getInput4() {
        return input4s;
    }

    @Override
    public U5s getInput5() {
        return input5s;
    }

    @Override
    public U6s getInput6() {
        return input6s;
    }

    @Override
    public U7s getInput7() {
        return input7s;
    }

    @Override
    public IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> compile(@NotNull T target, U1 input1, U2 input2, U3 input3, U4 input4, U5 input5, U6 input6, U7 input7)
            throws CompileException {
        return fn.compile(target, input1, input2, input3, input4, input5, input6, input7);
    }

    public static abstract class Builder<self_t, Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
            U1s extends IParameterizedTarget<Param, U1, U1K, U1T>, U1K, //
            U2s extends IParameterizedTarget<Param, U2, U2K, U2T>, U2K, //
            U3s extends IParameterizedTarget<Param, U3, U3K, U3T>, U3K, //
            U4s extends IParameterizedTarget<Param, U4, U4K, U4T>, U4K, //
            U5s extends IParameterizedTarget<Param, U5, U5K, U5T>, U5K, //
            U6s extends IParameterizedTarget<Param, U6, U6K, U6T>, U6K, //
            U7s extends IParameterizedTarget<Param, U7, U7K, U7T>, U7K, //
            T extends IKeyData<TK, TT>, TT, //
            U1 extends IKeyData<U1K, U1T>, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7T> {

        protected int priority;
        protected Tp pattern;
        protected CompileFunction7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn;
        protected U1s input1s;
        protected U2s input2s;
        protected U3s input3s;
        protected U4s input4s;
        protected U5s input5s;
        protected U6s input6s;
        protected U7s input7s;

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

        public self_t fn(@NotNull CompileFunction7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn) {
            this.fn = fn;
            return _this;
        }

        @SuppressWarnings("unchecked")
        public self_t input(@NotNull IParameterizedTarget<?, ?, ?, ?>... inputss) {
            this.input1s = (U1s) inputss[0];
            this.input2s = (U2s) inputss[1];
            this.input3s = (U3s) inputss[2];
            this.input4s = (U4s) inputss[3];
            this.input5s = (U5s) inputss[4];
            this.input6s = (U6s) inputss[5];
            this.input7s = (U7s) inputss[6];
            return _this;
        }

        public self_t input1(@NotNull U1s input1s) {
            this.input1s = input1s;
            return _this;
        }

        public self_t input2(@NotNull U2s input2s) {
            this.input2s = input2s;
            return _this;
        }

        public self_t input3(@NotNull U3s input3s) {
            this.input3s = input3s;
            return _this;
        }

        public self_t input4(@NotNull U4s input4s) {
            this.input4s = input4s;
            return _this;
        }

        public self_t input5(@NotNull U5s input5s) {
            this.input5s = input5s;
            return _this;
        }

        public self_t input6(@NotNull U6s input6s) {
            this.input6s = input6s;
            return _this;
        }

        public self_t input7(@NotNull U7s input7s) {
            this.input7s = input7s;
            return _this;
        }

    }

}
