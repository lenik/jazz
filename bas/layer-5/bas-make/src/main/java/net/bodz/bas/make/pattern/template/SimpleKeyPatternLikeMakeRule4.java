package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction4;
import net.bodz.bas.make.fn.IMakeable4;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleKeyPatternLikeMakeRule4<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        Ws extends IParameterizedKeys<Param, WK>, WK, //
        Xs extends IParameterizedKeys<Param, XK>, XK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT> //
        implements IKeyPatternLikeMakeRule4<Tp, Param, K, Keys, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT> {

    int priority;
    Tp pattern;
    CompileFunction4<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn;
    Us input1s;
    Vs input2s;
    Ws input3s;
    Xs input4s;

    public SimpleKeyPatternLikeMakeRule4(int priority, @NotNull Tp pattern, @NotNull CompileFunction4<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s) {
        this.priority = priority;
        this.pattern = pattern;
        this.fn = fn;
        this.input1s = input1s;
        this.input2s = input2s;
        this.input3s = input3s;
        this.input4s = input4s;
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
    public Ws getInput3() {
        return input3s;
    }

    @Override
    public Xs getInput4() {
        return input4s;
    }

    @Override
    public IMakeable4<TT, UT, VT, WT, XT> compile(@NotNull T target, U input1, V input2, W input3, X input4)
            throws CompileException {
        return fn.compile(target, input1, input2, input3, input4);
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<self_t, Tp extends IKeyPatternLike<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            Ws extends IParameterizedKeys<Param, WK>, WK, //
            Xs extends IParameterizedKeys<Param, XK>, XK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            , //
            V extends IKeyData<VK, VT>, VT
            , //
            W extends IKeyData<WK, WT>, WT
            , //
            X extends IKeyData<XK, XT>, XT
            > {

        protected int priority;
        protected Tp pattern;
        protected CompileFunction4<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn;
        protected Us input1s;
        protected Vs input2s;
        protected Ws input3s;
        protected Xs input4s;

        public self_t priority(int priority) {
            this.priority = priority;
            return (self_t) this;
        }

        public self_t pattern(@NotNull Tp pattern) {
            this.pattern = pattern;
            return (self_t) this;
        }

        public self_t fn(@NotNull CompileFunction4<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn) {
            this.fn = fn;
            return (self_t) this;
        }

        @SuppressWarnings("unchecked")
        public self_t input(@NotNull IParameterizedKeys<?, ?>... inputss) {
            this.input1s = (Us) inputss[0];
            this.input2s = (Vs) inputss[1];
            this.input3s = (Ws) inputss[2];
            this.input4s = (Xs) inputss[3];
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

        public self_t input3(@NotNull Ws input3s) {
            this.input3s = input3s;
            return (self_t) this;
        }

        public self_t input4(@NotNull Xs input4s) {
            this.input4s = input4s;
            return (self_t) this;
        }

    }

}
