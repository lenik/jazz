package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTarget2KeyPatternMakeRule<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, T, TT>
        implements IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> {

    public SimpleDataTypedTarget2KeyPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IDataTypedParameterizedKey<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    Builder<Tp, Param, TK, T, TT> builder() {
        return new Builder<Tp, Param, TK, T, TT>();
    }

    public static class Builder<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT>
            extends SimpleKeyPatternLikeMakeRule.Builder<Builder<Tp, Param, TK, T, TT>, Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, T, TT> {

        public SimpleDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IDataTypedParameterizedKey<?, ?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleDataTypedTarget2KeyPatternMakeRule<>(priority, pattern, inputs, fn);
        }

    }

}
