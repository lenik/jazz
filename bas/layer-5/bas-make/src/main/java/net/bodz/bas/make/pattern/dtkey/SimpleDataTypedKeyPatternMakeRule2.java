package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule2;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule2<Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT> //
        extends SimpleKeyPatternLikeMakeRule2<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, Vs, VK, T, TT, U, UT, V, VT>
        implements IDataTypedKeyPatternMakeRule2<Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

    public SimpleDataTypedKeyPatternMakeRule2(int priority, @NotNull Tp pattern, @NotNull CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn, @NotNull Us input1s, @NotNull Vs input2s) {
        super(priority, pattern, fn, input1s, input2s);
    }

    public static <S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    Builder<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
            extends SimpleKeyPatternLikeMakeRule2.Builder<Builder<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT>, //
            Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

        BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> apply(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedKeyPatternMakeRule2<Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            return new SimpleDataTypedKeyPatternMakeRule2<>(priority, pattern, fn, input1s, input2s);
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
