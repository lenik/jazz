package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule1<Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKeys<Param, UK>, UK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends SimpleKeyPatternLikeMakeRule1<Tp, Param, K, IParameterizedKeys<?, ?>, Us, UK, T, TT, U, UT>
        implements IKeyPatternMakeRule1<Tp, Param, K, Us, UK, T, TT, U, UT> {

    public SimpleKeyPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            > //
    Builder<Tp, Param, K, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            > //
            extends SimpleKeyPatternLikeMakeRule1.Builder<Builder<Tp, Param, K, Us, UK, T, TT, U, UT>, //
            Tp, Param, K, Us, UK, T, TT, U, UT> {

        public SimpleKeyPatternMakeRule1<Tp, Param, K, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleKeyPatternMakeRule1<>(priority, pattern, fn, input1s);
        }

    }

}
