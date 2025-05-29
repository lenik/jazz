package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule0;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule0<Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT> //
        extends SimpleKeyPatternLikeMakeRule0<Tp, Param, K, IParameterizedKeys<?, ?>, T, TT>
        implements IKeyPatternMakeRule0<Tp, Param, K, T, TT> {

    public SimpleKeyPatternMakeRule0(int priority, @NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        super(priority, pattern, fn);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    Builder<Tp, Param, K, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
            extends SimpleKeyPatternLikeMakeRule0.Builder<Builder<Tp, Param, K, T, TT>, //
            Tp, Param, K, T, TT> {

        public SimpleKeyPatternMakeRule0<Tp, Param, K, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleKeyPatternMakeRule0<>(priority, pattern, fn);
        }

    }

}
