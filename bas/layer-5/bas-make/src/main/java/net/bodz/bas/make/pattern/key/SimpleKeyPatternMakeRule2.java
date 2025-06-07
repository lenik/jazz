package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule2;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule2<Tp extends IKeyPattern<Param, TK>, Param, TK, Us extends IParameterizedKey<Param, UK>, //
        Vs extends IParameterizedKey<Param, VK>, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT> //
        extends SimpleKeyPatternLikeMakeRule2<Tp, Param, TK, IParameterizedKey<?, ?>, Us, Vs, T, TT, U, UK, UT, V, VK, VT>
        implements IKeyPatternMakeRule2<Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> {

    public SimpleKeyPatternMakeRule2(int priority, @NotNull Tp pattern, @NotNull CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn, @NotNull Us input1s, @NotNull Vs input2s) {
        super(priority, pattern, fn, input1s, input2s);
    }

    public static <S, Tp extends IKeyPattern<Param, TK>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
    Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IKeyPattern<Param, TK>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, //
            Vs extends IParameterizedKey<Param, VK>, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
            extends SimpleKeyPatternLikeMakeRule2.Builder<Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT>, //
            Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> {

        BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> apply(BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleKeyPatternMakeRule2<Tp, Param, TK, Us, Vs, T, TT, U, UK, UT, V, VK, VT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            return new SimpleKeyPatternMakeRule2<>(priority, pattern, fn, input1s, input2s);
        }

    public void make(CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn) {
        make(subject, fn);
    }

    public void make(S subject, CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn) {
        if (subject == null)
            throw new NullPointerException("subject");
        this.fn = fn;
        apply.accept(subject, build());
    }

    }

}
