package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction3;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule3;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTarget2KeyPatternMakeRule3<Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, Us extends IParameterizedKey<Param, UK>, UK, //
        Vs extends IParameterizedKey<Param, VK>, VK, //
        Ws extends IParameterizedKey<Param, WK>, WK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT> //
        extends SimpleKeyPatternLikeMakeRule3<Tp, Param, TK, IParameterizedKey<?, ?>, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>
        implements ITarget2KeyPatternMakeRule3<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> {

    public SimpleTarget2KeyPatternMakeRule3(int priority, @NotNull Tp pattern, @NotNull CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s) {
        super(priority, pattern, fn, input1s, input2s, input3s);
    }

    public static <S, Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
            extends SimpleKeyPatternLikeMakeRule3.Builder<Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>, //
            Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> {

        BiConsumer<S, ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> apply(BiConsumer<S, ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleTarget2KeyPatternMakeRule3<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> build() {
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
            return new SimpleTarget2KeyPatternMakeRule3<>(priority, pattern, fn, input1s, input2s, input3s);
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
