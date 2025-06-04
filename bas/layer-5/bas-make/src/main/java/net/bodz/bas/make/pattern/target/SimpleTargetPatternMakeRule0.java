package net.bodz.bas.make.pattern.target;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.pattern.template.SimpleTargetPatternLikeMakeRule0;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetPatternMakeRule0<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
        extends SimpleTargetPatternLikeMakeRule0<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, T, TT>
        implements ITargetPatternMakeRule0<Tp, Param, TK, T, TT> {

    public SimpleTargetPatternMakeRule0(int priority, @NotNull Tp pattern, @NotNull CompileFunction0<T, TK, TT> fn) {
        super(priority, pattern, fn);
    }

    public static <S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    Builder<S, Tp, Param, TK, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<S, Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
            extends SimpleTargetPatternLikeMakeRule0.Builder<Builder<S, Tp, Param, TK, T, TT>, //
            Tp, Param, TK, T, TT> {

        BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply;
        S subject;

        public Builder<S, Tp, Param, TK, T, TT> apply(BiConsumer<S, ITargetPatternMakeRule<Tp, Param, TK, T, TT>> apply) {
            this.apply = apply;
            return this;
        }

        public Builder<S, Tp, Param, TK, T, TT> subject(S subject) {
            this.subject = subject;
            return this;
        }

        public SimpleTargetPatternMakeRule0<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleTargetPatternMakeRule0<>(priority, pattern, fn);
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
