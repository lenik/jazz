package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.fn.IMakeable0;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleKeyPatternLikeMakeRule0<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        T extends IKeyData<K, TT>, TT> //
        implements IKeyPatternLikeMakeRule0<Tp, Param, K, Keys, T, TT> {

    int priority;
    Tp pattern;
    CompileFunction0<T, K, TT> fn;

    public SimpleKeyPatternLikeMakeRule0(int priority, @NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        this.priority = priority;
        this.pattern = pattern;
        this.fn = fn;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Tp getPattern() {
        return pattern;
    }

    @Override
    public IMakeable0<TT> compile(@NotNull T target)
            throws CompileException {
        return fn.compile(target);
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<self_t, Tp extends IKeyPatternLike<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> {

        protected int priority;
        protected Tp pattern;
        protected CompileFunction0<T, K, TT> fn;

        public self_t priority(int priority) {
            this.priority = priority;
            return (self_t) this;
        }

        public self_t pattern(@NotNull Tp pattern) {
            this.pattern = pattern;
            return (self_t) this;
        }

        public self_t fn(@NotNull CompileFunction0<T, K, TT> fn) {
            this.fn = fn;
            return (self_t) this;
        }

        @SuppressWarnings("unchecked")
        public self_t input(@NotNull IParameterizedKeys<?, ?>... inputss) {
            return (self_t) this;
        }

    }

}
