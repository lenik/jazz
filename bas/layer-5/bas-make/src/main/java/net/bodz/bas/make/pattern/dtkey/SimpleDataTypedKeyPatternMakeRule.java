package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        T extends IKeyData<K, TT>, TT>
        extends SimpleKeyPatternLikeMakeRule<Tp, Param, K, IDataTypedParameterizedKeys<?, ?, ?>, T, TT>
        implements IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    public SimpleDataTypedKeyPatternMakeRule(int priority, @NotNull Tp pattern, @NotNull IDataTypedParameterizedKeys<?, ?, ?>[] inputss, @NotNull CompileFunction<T> fn) {
        super(priority, pattern, inputss, fn);
    }

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    Builder<Tp, Param, K, T, TT> builder() {
        return new Builder<Tp, Param, K, T, TT>();
    }

    public static class Builder<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT>
            extends SimpleKeyPatternLikeMakeRule.Builder<Builder<Tp, Param, K, T, TT>, Tp, Param, K, IDataTypedParameterizedKeys<?, ?, ?>, T, TT> {

        public SimpleDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (inputs == null)
                inputs = new IDataTypedParameterizedKeys<?, ?, ?>[0];
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleDataTypedKeyPatternMakeRule<>(priority, pattern, inputs, fn);
        }

    }

}
