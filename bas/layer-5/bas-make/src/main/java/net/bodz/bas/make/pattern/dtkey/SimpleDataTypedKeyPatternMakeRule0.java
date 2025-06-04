package net.bodz.bas.make.pattern.dtkey;

import java.util.function.BiConsumer;

import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule0;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule0<Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> //
        extends SimpleKeyPatternLikeMakeRule0<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, T, TT>
        implements IDataTypedKeyPatternMakeRule0<Tp, Param, TK, T, TT> {

    public SimpleDataTypedKeyPatternMakeRule0(int priority, @NotNull Tp pattern, @NotNull CompileFunction0<T, TK, TT> fn) {
        super(priority, pattern, fn);
    }

    public static <S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    Builder<S, Tp, Param, TK, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IDataTypedKeyPattern<Param, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
            extends SimpleKeyPatternLikeMakeRule0.Builder<Builder<S, Tp, Param, TK, T, TT>, //
            Tp, Param, TK, T, TT> {

        BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, T, TT> apply(BiConsumer<S, IDataTypedKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleDataTypedKeyPatternMakeRule0<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleDataTypedKeyPatternMakeRule0<>(priority, pattern, fn);
        }

        public void make(CompileFunction0<T, TK, TT> fn) {
            make(subject, fn);
        }

        public void make(S subject, CompileFunction0<T, TK, TT> fn) {
            if (subject == null)
                throw new NullPointerException("subject");
            this.fn = fn;
            apply.accept(subject, build());
        }

    }

}
