package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTarget2KeyPatternMakeRule1<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends SimpleKeyPatternLikeMakeRule1<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, T, TT, U, UT>
        implements IDataTypedTarget2KeyPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> {

    public SimpleDataTypedTarget2KeyPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    Builder<Tp, Param, TK, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
            extends SimpleKeyPatternLikeMakeRule1.Builder<Builder<Tp, Param, TK, Us, UK, T, TT, U, UT>, //
            Tp, Param, TK, Us, UK, T, TT, U, UT> {

        public SimpleDataTypedTarget2KeyPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleDataTypedTarget2KeyPatternMakeRule1<>(priority, pattern, fn, input1s);
        }

    }

}
