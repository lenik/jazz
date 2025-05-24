package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule2;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule2<Tp extends IKeyPattern<Param, K>, Param, K, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT>
        extends SimpleKeyPatternLikeMakeRule2<Tp, Param, K, IParameterizedKeys<?, ?>, Us, UK, Vs, VK, T, TT, U, UT, V, VT>
        implements IKeyPatternMakeRule2<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

    public SimpleKeyPatternMakeRule2(int priority, @NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
        super(priority, pattern, input1s, input2s, fn);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKeys<Param, UK>, UK, //
            Vs extends IParameterizedKeys<Param, VK>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT>
            extends SimpleKeyPatternLikeMakeRule2.Builder<Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT>, //
            Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

        public SimpleKeyPatternMakeRule2<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleKeyPatternMakeRule2<>(priority, pattern, input1s, input2s, fn);
        }

    }

}
