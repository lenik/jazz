package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.fn.CompileFunction6;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule6;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTarget2KeyPatternMakeRule6<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
        Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
        Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
        Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, ZK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT, //
        Z extends IKeyData<ZK, ZT>, ZT> //
        extends SimpleKeyPatternLikeMakeRule6<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>
        implements IDataTypedTarget2KeyPatternMakeRule6<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> {

    public SimpleDataTypedTarget2KeyPatternMakeRule6(int priority, @NotNull Tp pattern, @NotNull CompileFunction6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull Zs input6s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public static <S, Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
            Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, ZK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
            Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, ZK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
            extends SimpleKeyPatternLikeMakeRule6.Builder<Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>, //
            Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> {

        BiConsumer<S, IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> apply(BiConsumer<S, IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedTarget2KeyPatternMakeRule6<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            if (input3s == null)
                throw new NullPointerException("input3s");
            if (input4s == null)
                throw new NullPointerException("input4s");
            if (input5s == null)
                throw new NullPointerException("input5s");
            if (input6s == null)
                throw new NullPointerException("input6s");
            return new SimpleDataTypedTarget2KeyPatternMakeRule6<>(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s);
        }

        public void make(CompileFunction6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
