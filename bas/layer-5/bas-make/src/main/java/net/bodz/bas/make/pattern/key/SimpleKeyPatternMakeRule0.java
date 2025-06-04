package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule0;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule0<Tp extends IKeyPattern<Param, TK>, Param, TK, T extends IKeyData<TK, TT>, TT> //
        extends SimpleKeyPatternLikeMakeRule0<Tp, Param, TK, IParameterizedKey<?, ?>, T, TT>
        implements IKeyPatternMakeRule0<Tp, Param, TK, T, TT> {

    public SimpleKeyPatternMakeRule0(int priority, @NotNull Tp pattern, @NotNull CompileFunction0<T, TK, TT> fn) {
        super(priority, pattern, fn);
    }

    public static <S, Tp extends IKeyPattern<Param, TK>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    Builder<S, Tp, Param, TK, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends IKeyPattern<Param, TK>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
            extends SimpleKeyPatternLikeMakeRule0.Builder<Builder<S, Tp, Param, TK, T, TT>, //
            Tp, Param, TK, T, TT> {

        BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, T, TT> apply(BiConsumer<S, IKeyPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleKeyPatternMakeRule0<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleKeyPatternMakeRule0<>(priority, pattern, fn);
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
