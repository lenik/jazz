package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetPatternMakeRule<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, TK, IParameterizedKeys<?, ?>, T, TT>
        implements ITargetPatternMakeRule<Tp, Param, TK, T, TT> {

    public SimpleTargetPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IParameterizedKeys<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    Builder<Tp, Param, TK, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT>
            extends SimpleKeyPatternLikeMakeRule.Builder<Builder<Tp, Param, TK, T, TT>, //
            Tp, Param, TK, IParameterizedKeys<?, ?>, T, TT> {

        public SimpleTargetPatternMakeRule<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IParameterizedKeys<?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleTargetPatternMakeRule<>(priority, pattern, inputs, fn);
        }

    }

}
