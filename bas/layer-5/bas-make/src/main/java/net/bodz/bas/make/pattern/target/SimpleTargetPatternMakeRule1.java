package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.CompileFunction1;
import net.bodz.bas.make.pattern.template.SimpleTargetPatternLikeMakeRule1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetPatternMakeRule1<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends SimpleTargetPatternLikeMakeRule1<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, Us, UK, T, TT, U, UT>
        implements ITargetPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> {

    public SimpleTargetPatternMakeRule1(int priority, @NotNull Tp pattern, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn, @NotNull Us input1s) {
        super(priority, pattern, fn, input1s);
    }

    public static <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    Builder<Tp, Param, TK, Us, UK, T, TT, U, UT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
            extends SimpleTargetPatternLikeMakeRule1.Builder<Builder<Tp, Param, TK, Us, UK, T, TT, U, UT>, //
            Tp, Param, TK, Us, UK, T, TT, U, UT> {

        public SimpleTargetPatternMakeRule1<Tp, Param, TK, Us, UK, T, TT, U, UT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            return new SimpleTargetPatternMakeRule1<>(priority, pattern, fn, input1s);
        }

    }

}
