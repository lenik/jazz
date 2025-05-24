package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.meta.decl.NotNull;

public abstract class SimpleKeyPatternLikeMakeRule<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        T extends IKeyData<K, TT>, TT>
        implements IKeyPatternLikeMakeRule<Tp, Param, K, Keys, T, TT> {

    int priority;
    Tp pattern;
    Keys[] inputss;
    CompileFunction<T> fn;

    public SimpleKeyPatternLikeMakeRule(int priority, @NotNull Tp pattern, @NotNull Keys[] inputss, @NotNull CompileFunction<T> fn) {
        this.priority = priority;
        this.pattern = pattern;
        this.inputss = inputss;
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
    public Keys[] getInputs() {
        return inputss;
    }

    @Override
    public MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException {
        return fn.compile(target, inputs);
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<self_t, Tp extends IKeyPatternLike<Param, K>, Param, K, //
            Keys extends IParameterizedKeys<?, ?>, //
            T extends IKeyData<K, TT>, TT> {

        protected int priority;
        protected Tp pattern;
        protected Keys[] inputs;
        protected CompileFunction<T> fn;

        public self_t priority(int priority) {
            this.priority = priority;
            return (self_t) this;
        }

        public self_t pattern(Tp pattern) {
            this.pattern = pattern;
            return (self_t) this;
        }

        public self_t input(@NotNull Keys... inputs) {
            this.inputs = inputs;
            return (self_t) this;
        }

        public self_t fn(CompileFunction<T> fn) {
            this.fn = fn;
            return (self_t) this;
        }

    }

}
