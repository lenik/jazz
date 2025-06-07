package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.fn.CompileFunction3;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule3;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule3<Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT> //
        extends SimpleKeyPatternLikeMakeRule3<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT>
        implements IDataTypedKeyPatternMakeRule3<Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT> {

    public SimpleDataTypedKeyPatternMakeRule3(int priority, @NotNull Tp pattern, @NotNull CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn, 
            @NotNull Us input1s, 
            @NotNull Vs input2s, 
            @NotNull Ws input3s) {
        super(priority, pattern, fn, input1s, input2s, input3s);
    }

    public static <S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> //
    Builder<S, Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> //
            extends SimpleKeyPatternLikeMakeRule3.Builder<Builder<S, Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT>, //
            Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT> {

        BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT> apply(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedKeyPatternMakeRule3<Tp, Param, TK, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT> build() {
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
            return new SimpleDataTypedKeyPatternMakeRule3<>(priority, pattern, fn, input1s, input2s, input3s);
        }

        public void make(CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
