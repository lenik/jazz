package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction3;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule3;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedTargetPatternMakeRule3<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
        Ws extends IDataTypedParameterizedKeys<Param, WK, WT>, WK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT> //
        extends SimpleKeyPatternLikeMakeRule3<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>
        implements IDataTypedTargetPatternMakeRule3<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> {

    public SimpleDataTypedTargetPatternMakeRule3(int priority, @NotNull Tp pattern, @NotNull CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s) {
        super(priority, pattern, fn, input1s, input2s, input3s);
    }

    public static <Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKeys<Param, WK, WT>, WK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    Builder<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKeys<Param, WK, WT>, WK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
            extends SimpleKeyPatternLikeMakeRule3.Builder<Builder<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>, //
            Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> {

        public SimpleDataTypedTargetPatternMakeRule3<Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            if (input3s == null)
                throw new NullPointerException("input3s");
            return new SimpleDataTypedTargetPatternMakeRule3<>(priority, pattern, fn, input1s, input2s, input3s);
        }

    }

}
