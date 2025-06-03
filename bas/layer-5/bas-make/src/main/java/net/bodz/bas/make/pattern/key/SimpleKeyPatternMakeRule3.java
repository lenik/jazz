package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.fn.CompileFunction3;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule3;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule3<Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKey<Param, UK>, UK, //
        Vs extends IParameterizedKey<Param, VK>, VK, //
        Ws extends IParameterizedKey<Param, WK>, WK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT> //
        extends SimpleKeyPatternLikeMakeRule3<Tp, Param, K, IParameterizedKey<?, ?>, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>
        implements IKeyPatternMakeRule3<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> {

    public SimpleKeyPatternMakeRule3(int priority, @NotNull Tp pattern, @NotNull CompileFunction3<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s) {
        super(priority, pattern, fn, input1s, input2s, input3s);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            , //
            V extends IKeyData<VK, VT>, VT
            , //
            W extends IKeyData<WK, WT>, WT
            > //
    Builder<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT
            , //
            V extends IKeyData<VK, VT>, VT
            , //
            W extends IKeyData<WK, WT>, WT
            > //
            extends SimpleKeyPatternLikeMakeRule3.Builder<Builder<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>, //
            Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> {

        public SimpleKeyPatternMakeRule3<Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT> build() {
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
            return new SimpleKeyPatternMakeRule3<>(priority, pattern, fn, input1s, input2s, input3s);
        }

    }

}
