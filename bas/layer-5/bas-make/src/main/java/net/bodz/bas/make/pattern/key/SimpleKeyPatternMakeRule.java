package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule<Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, K, IParameterizedKey<?, ?>, T, TT>
        implements IKeyPatternMakeRule<Tp, Param, K, T, TT> {

    public SimpleKeyPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
    Builder<Tp, Param, K, T, TT> builder() {
        return new Builder<Tp, Param, K, T, TT>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
            extends SimpleKeyPatternLikeMakeRule.Builder<Builder<Tp, Param, K, T, TT>, //
            Tp, Param, K, IParameterizedKey<?, ?>, T, TT> {

        public SimpleKeyPatternMakeRule<Tp, Param, K, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IParameterizedKey<?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleKeyPatternMakeRule<>(priority, pattern, inputs, fn);
        }

    }

}
