package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.fn.CompileFunction6;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule6;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule6<Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
        Xs extends IDataTypedParameterizedKey<Param, XK, XT>, //
        Ys extends IDataTypedParameterizedKey<Param, YK, YT>, //
        Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT, //
        Y extends IKeyData<YK, YT>, YK, YT, //
        Z extends IKeyData<ZK, ZT>, ZK, ZT> //
        extends SimpleKeyPatternLikeMakeRule6<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>
        implements IDataTypedKeyPatternMakeRule6<Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> {

    public SimpleDataTypedKeyPatternMakeRule6(int priority, @NotNull Tp pattern, @NotNull CompileFunction6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn, 
            @NotNull Us input1s, 
            @NotNull Vs input2s, 
            @NotNull Ws input3s, 
            @NotNull Xs input4s, 
            @NotNull Ys input5s, 
            @NotNull Zs input6s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s);
    }

    public static <S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, //
            Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT> //
    Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, //
            Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT> //
            extends SimpleKeyPatternLikeMakeRule6.Builder<Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>, //
            Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> {

        BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> apply(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedKeyPatternMakeRule6<Tp, Param, TK, Us, Vs, Ws, Xs, Ys, Zs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> build() {
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
            return new SimpleDataTypedKeyPatternMakeRule6<>(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s);
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
