package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule0;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule0<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        T extends IKeyData<K, TT>, TT
        > //
        extends SimpleKeyPatternLikeMakeRule0<Tp, Param, K, IDataTypedParameterizedKeys<?, ?, ?>, T, TT>
        implements IDataTypedKeyPatternMakeRule0<Tp, Param, K, T, TT> {

    public SimpleDataTypedKeyPatternMakeRule0(int priority, @NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        super(priority, pattern, fn);
    }

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    Builder<Tp, Param, K, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
            extends SimpleKeyPatternLikeMakeRule0.Builder<Builder<Tp, Param, K, T, TT>, //
            Tp, Param, K, T, TT> {

        public SimpleDataTypedKeyPatternMakeRule0<Tp, Param, K, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleDataTypedKeyPatternMakeRule0<>(priority, pattern, fn);
        }

    }

}
