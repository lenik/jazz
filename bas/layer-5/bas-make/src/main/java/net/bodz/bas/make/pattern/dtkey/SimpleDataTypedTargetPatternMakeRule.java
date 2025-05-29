package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTargetPatternMakeRule<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, T, TT>
        implements IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT> {

    public SimpleDataTypedTargetPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IDataTypedParameterizedKeys<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    Builder<Tp, Param, TK, T, TT> builder() {
        return new Builder<Tp, Param, TK, T, TT>();
    }

    public static class Builder<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT>
            extends SimpleKeyPatternLikeMakeRule.Builder<Builder<Tp, Param, TK, T, TT>, Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, T, TT> {

        public SimpleDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IDataTypedParameterizedKeys<?, ?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleDataTypedTargetPatternMakeRule<>(priority, pattern, inputs, fn);
        }

    }

}
