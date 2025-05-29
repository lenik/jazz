package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule2;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleTargetPatternMakeRule2<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT> //
        extends SimpleKeyPatternLikeMakeRule2<Tp, Param, TK, IParameterizedKeys<?, ?>, Us, UK, Vs, VK, T, TT, U, UT, V, VT>
        implements ITargetPatternMakeRule2<Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

    public SimpleTargetPatternMakeRule2(int priority, @NotNull Tp pattern, @NotNull CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn, @NotNull Us input1s, @NotNull Vs input2s) {
        super(priority, pattern, fn, input1s, input2s);
    }

    public static <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            , //
            V extends IKeyData<VK, VT>, VT
            > //
    Builder<Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            , //
            V extends IKeyData<VK, VT>, VT
            > //
            extends SimpleKeyPatternLikeMakeRule2.Builder<Builder<Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT>, //
            Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

        public SimpleTargetPatternMakeRule2<Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            return new SimpleTargetPatternMakeRule2<>(priority, pattern, fn, input1s, input2s);
        }

    }

}
