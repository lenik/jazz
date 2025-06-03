package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTarget2KeyPatternMakeRule<Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, TK, IParameterizedKey<?, ?>, T, TT>
        implements ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> {

    public SimpleTarget2KeyPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IParameterizedKey<?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT> //
    Builder<Tp, Param, TK, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, T extends IKeyData<TK, TT>, TT>
            extends SimpleKeyPatternLikeMakeRule.Builder<Builder<Tp, Param, TK, T, TT>, //
            Tp, Param, TK, IParameterizedKey<?, ?>, T, TT> {

        public SimpleTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IParameterizedKey<?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleTarget2KeyPatternMakeRule<>(priority, pattern, inputs, fn);
        }

    }

}
