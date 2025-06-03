package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTarget2KeyPatternMakeRule1<Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, Us extends IParameterizedKey<Param, UK>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends SimpleKeyPatternLikeMakeRule1<Tp, Param, TK, IParameterizedKey<?, ?>, Us, UK, T, TT, U, UT>
        implements ITarget2KeyPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> {

    public SimpleTarget2KeyPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    Builder<Tp, Param, TK, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
            extends SimpleKeyPatternLikeMakeRule1.Builder<Builder<Tp, Param, TK, Us, UK, T, TT, U, UT>, //
            Tp, Param, TK, Us, UK, T, TT, U, UT> {

        public SimpleTarget2KeyPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleTarget2KeyPatternMakeRule1<>(priority, pattern, fn, input1s);
        }

    }

}
