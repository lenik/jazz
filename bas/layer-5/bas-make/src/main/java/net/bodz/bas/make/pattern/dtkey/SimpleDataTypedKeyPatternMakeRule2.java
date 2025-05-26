package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.fn.CompileFunction2;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule2;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleDataTypedKeyPatternMakeRule2<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
        T extends IKeyData<K, TT>, TT
        , //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT> //
        extends SimpleKeyPatternLikeMakeRule2<Tp, Param, K, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, Vs, VK, T, TT, U, UT, V, VT>
        implements IDataTypedKeyPatternMakeRule2<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

    public SimpleDataTypedKeyPatternMakeRule2(int priority, @NotNull Tp pattern, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn, @NotNull Us input1s, @NotNull Vs input2s) {
        super(priority, pattern, fn, input1s, input2s);
    }

    public static <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
            extends SimpleKeyPatternLikeMakeRule2.Builder<Builder<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT>, //
            Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> {

        public SimpleDataTypedKeyPatternMakeRule2<Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT> build() {
            if (pattern == null)
                throw new NullPointerException("pattern");
            if (fn == null)
                throw new NullPointerException("fn");
            if (input1s == null)
                throw new NullPointerException("input1s");
            if (input2s == null)
                throw new NullPointerException("input2s");
            return new SimpleDataTypedKeyPatternMakeRule2<>(priority, pattern, fn, input1s, input2s);
        }

    }

}
