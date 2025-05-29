package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTargetPatternMakeRule1<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends SimpleKeyPatternLikeMakeRule1<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, T, TT, U, UT>
        implements IDataTypedTargetPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> {

    public SimpleDataTypedTargetPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    Builder<Tp, Param, TK, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
            extends SimpleKeyPatternLikeMakeRule1.Builder<Builder<Tp, Param, TK, Us, UK, T, TT, U, UT>, //
            Tp, Param, TK, Us, UK, T, TT, U, UT> {

        public SimpleDataTypedTargetPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleDataTypedTargetPatternMakeRule1<>(priority, pattern, fn, input1s);
        }

    }

}
