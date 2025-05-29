package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule1<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends SimpleKeyPatternLikeMakeRule1<Tp, Param, K, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, T, TT, U, UT>
        implements IDataTypedKeyPatternMakeRule1<Tp, Param, K, Us, UK, T, TT, U, UT> {

    public SimpleDataTypedKeyPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    Builder<Tp, Param, K, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
            extends SimpleKeyPatternLikeMakeRule1.Builder<Builder<Tp, Param, K, Us, UK, T, TT, U, UT>, //
            Tp, Param, K, Us, UK, T, TT, U, UT> {

        public SimpleDataTypedKeyPatternMakeRule1<Tp, Param, K, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleDataTypedKeyPatternMakeRule1<>(priority, pattern, fn, input1s);
        }

    }

}
