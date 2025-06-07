package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;
import java.util.function.Function;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.fn.Function1;
import net.bodz.bas.make.fn.Function2;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyExtendsPatternMakeRule<Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, K, IParameterizedKey<?, ?>, T, TT>
        implements IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT> {

    public SimpleKeyExtendsPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <S, Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    Builder<S, Tp, Param, K, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
            extends AbstractBuilder<Builder<S, Tp, Param, K, T, TT>, //
            Tp, Param, K, IParameterizedKey<?, ?>, T, TT> {

        BiConsumer<S, IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> apply;
        S subject;

        @Override
        public IParameterizedKey<?, ?>[] defaultInputs() {
            return new IParameterizedKey[inputCount()];
        }

        public Builder<S, Tp, Param, K, T, TT> apply(BiConsumer<S, IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, K, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleKeyExtendsPatternMakeRule<Tp, Param, K, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleKeyExtendsPatternMakeRule<>(priority, pattern, inputs, fn);
        }

        public void make(CompileFunction<T> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction<T> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

    public static class Dispatcher<S, Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
            extends SimpleKeyExtendsPatternMakeRuleBuilders<S, Tp, Param, K, T, TT> {

        BiConsumer<S, IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> apply;
        S subject;
        Tp pattern;

        public Dispatcher(BiConsumer<S, IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> apply) {
            this.apply = apply;
        }

        public Dispatcher(BiConsumer<S, IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> apply, S subject, Tp pattern) {
            this.apply = apply;
            this.subject = subject;
            this.pattern = pattern;
        }

        @Override
        public BiConsumer<S, IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> getApply() {
            return apply;
        }

        @Override
        public S getSubject() {
            return subject;
        }

        @Override
        public Tp getPattern() {
            return pattern;
        }

        public Dispatcher<S, Tp, Param, K, T, TT> apply(BiConsumer<S, IKeyExtendsPatternMakeRule<Tp, Param, K, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Dispatcher<S, Tp, Param, K, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public Dispatcher<S, Tp, Param, K, T, TT> pattern(Tp pattern) {
            this.pattern = pattern;
            return this;
        }

        public SimpleKeyExtendsPatternMakeRule.Builder<S, Tp, Param, K, T, TT> input(@NotNull IParameterizedKey<?, ?>... inputss) {
            return SimpleKeyExtendsPatternMakeRule.<S, Tp, Param, K, T, TT>builder()//
                    .pattern(pattern) //
                    .input(inputss) //
                    .apply(apply).subject(subject);
        }

    }

    public static class A1<K, T1> {

        Class<? extends K> keyType;
        Class<T1> type1;

        public A1(Class<T1> type1) {
            this.type1 = type1;
        }

        public A1(Class<? extends K> keyType, Class<T1> type1) {
            this.keyType = keyType;
            this.type1 = type1;
        }

        public <Param> B1<Param, K, T1> parameter(Class<Param> parameterType, //
                Function1<T1, Param> fn) {
            return new B1<Param, K, T1>(parameterType, // keyType, //
                    type1, fn);
        }

    }

    public static class A2<K, T1, T2> {

        Class<? extends K> keyType;
        Class<T1> type1;
        Class<T2> type2;

        public A2(Class<? extends K> keyType, Class<T1> type1, Class<T2> type2) {
            this(type1, type2);
            this.keyType = keyType;
        }

        public A2(Class<T1> type1, Class<T2> type2) {
            this.type1 = type1;
            this.type2 = type2;
        }

        public <Param> B2<Param, K, T1, T2> parameter(Class<Param> parameterType, //
                Function2<T1, T2, Param> fn) {
            return new B2<>(parameterType, // keyType, //
                    type1, type2, fn);
        }

    }

    public static class B1<Param, K, T1> {

        Class<Param> parameterType;
        Class<? extends K> keyType;
        Class<T1> type1;
        Function1<T1, Param> parameterFn;
        IKeyExtendsPattern1<Param, K, T1> pattern;
        Function<Param, T1> fn1;

        public B1(Class<Param> parameterType, // Class<? extends K> keyType, //
                Class<T1> type1, Function1<T1, Param> parameterFn) {
            this.parameterType = parameterType;
//            this.keyType = keyType;
            this.type1 = type1;
            this.parameterFn = parameterFn;
//            pattern = new SimpleKeyExtendsPattern1<>(parameterType, keyType, type1, parameterFn);
            pattern = new SimpleKeyExtendsPattern1<>(parameterType, type1, parameterFn);
        }

    }

    public static class B2<Param, K, T1, T2> {

        Class<Param> parameterType;
        Class<? extends K> keyType;
        Class<T1> type1;
        Class<T2> type2;
        Function2<T1, T2, Param> parameterFn;
        IKeyExtendsPattern2<Param, K, T1, T2> pattern;
        Function<Param, T1> fn1;
        Function<Param, T2> fn2;

        public B2(Class<Param> parameterType, // Class<? extends K> keyType, //
                Class<T1> type1, Class<T2> type2, //
                Function2<T1, T2, Param> parameterFn) {
            this.parameterType = parameterType;
//            this.keyType = keyType;
            this.type1 = type1;
            this.parameterFn = parameterFn;
            // pattern = new SimpleKeyExtendsPattern2<>(parameterType, type1, type2, parameterFn);
        }

    }

}