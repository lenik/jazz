package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction6;
import net.bodz.bas.make.fn.IMakeable6;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleKeyPatternLikeMakeRule6<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKey<?, ?>, //
        Us extends IParameterizedKey<Param, UK>, //
        Vs extends IParameterizedKey<Param, VK>, //
        Ws extends IParameterizedKey<Param, WK>, //
        Xs extends IParameterizedKey<Param, XK>, //
        Ys extends IParameterizedKey<Param, YK>, //
        Zs extends IParameterizedKey<Param, ZK>, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT, //
        Y extends IKeyData<YK, YT>, YK, YT, //
        Z extends IKeyData<ZK, ZT>, ZK, ZT> //
        implements IKeyPatternLikeMakeRule6<Tp, Param, K, Keys, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> {

    int priority;
    Tp pattern;
    CompileFunction6<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn;
    Us input1s;
    Vs input2s;
    Ws input3s;
    Xs input4s;
    Ys input5s;
    Zs input6s;

    public SimpleKeyPatternLikeMakeRule6(int priority, @NotNull Tp pattern, @NotNull CompileFunction6<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull Zs input6s) {
        this.priority = priority;
        this.pattern = pattern;
        this.fn = fn;
        this.input1s = input1s;
        this.input2s = input2s;
        this.input3s = input3s;
        this.input4s = input4s;
        this.input5s = input5s;
        this.input6s = input6s;
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
    public Ys getInput5() {
        return input5s;
    }

    @Override
    public Zs getInput6() {
        return input6s;
    }

    @Override
    public IMakeable6<TT, UT, VT, WT, XT, YT, ZT> compile(@NotNull T target, U input1, V input2, W input3, X input4, Y input5, Z input6)
            throws CompileException {
        return fn.compile(target, input1, input2, input3, input4, input5, input6);
    }

    public static abstract class Builder<self_t, Tp extends IKeyPatternLike<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            Ws extends IParameterizedKey<Param, WK>, //
            Xs extends IParameterizedKey<Param, XK>, //
            Ys extends IParameterizedKey<Param, YK>, //
            Zs extends IParameterizedKey<Param, ZK>, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT> {

        protected int priority;
        protected Tp pattern;
        protected CompileFunction6<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn;
        protected Us input1s;
        protected Vs input2s;
        protected Ws input3s;
        protected Xs input4s;
        protected Ys input5s;
        protected Zs input6s;

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

        public self_t fn(@NotNull CompileFunction6<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn) {
            this.fn = fn;
            return _this;
        }

        @SuppressWarnings("unchecked")
        public self_t input(@NotNull IParameterizedKey<?, ?>... inputss) {
            this.input1s = (Us) inputss[0];
            this.input2s = (Vs) inputss[1];
            this.input3s = (Ws) inputss[2];
            this.input4s = (Xs) inputss[3];
            this.input5s = (Ys) inputss[4];
            this.input6s = (Zs) inputss[5];
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

        public self_t input3(@NotNull Ws input3s) {
            this.input3s = input3s;
            return _this;
        }

        public self_t input4(@NotNull Xs input4s) {
            this.input4s = input4s;
            return _this;
        }

        public self_t input5(@NotNull Ys input5s) {
            this.input5s = input5s;
            return _this;
        }

        public self_t input6(@NotNull Zs input6s) {
            this.input6s = input6s;
            return _this;
        }

    }

}
