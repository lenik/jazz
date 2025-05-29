package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction0;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule0;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTargetPatternMakeRule0<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> //
        extends SimpleKeyPatternLikeMakeRule0<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, T, TT>
        implements IDataTypedTargetPatternMakeRule0<Tp, Param, TK, T, TT> {

    public SimpleDataTypedTargetPatternMakeRule0(int priority, @NotNull Tp pattern, @NotNull CompileFunction0<T, TK, TT> fn) {
        super(priority, pattern, fn);
    }

    public static <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    Builder<Tp, Param, TK, T, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
            extends SimpleKeyPatternLikeMakeRule0.Builder<Builder<Tp, Param, TK, T, TT>, //
            Tp, Param, TK, T, TT> {

        public SimpleDataTypedTargetPatternMakeRule0<Tp, Param, TK, T, TT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleDataTypedTargetPatternMakeRule0<>(priority, pattern, fn);
        }

    }

}
