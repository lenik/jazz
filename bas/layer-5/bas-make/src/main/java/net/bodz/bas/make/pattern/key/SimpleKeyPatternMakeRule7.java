package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.fn.CompileFunction7;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.SimpleKeyPatternLikeMakeRule7;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyPatternMakeRule7<Tp extends IKeyPattern<Param, K>, Param, K, U1s extends IParameterizedKeys<Param, U1K>, U1K, //
        U2s extends IParameterizedKeys<Param, U2K>, U2K, //
        U3s extends IParameterizedKeys<Param, U3K>, U3K, //
        U4s extends IParameterizedKeys<Param, U4K>, U4K, //
        U5s extends IParameterizedKeys<Param, U5K>, U5K, //
        U6s extends IParameterizedKeys<Param, U6K>, U6K, //
        U7s extends IParameterizedKeys<Param, U7K>, U7K, //
        T extends IKeyData<K, TT>, TT, //
        U1 extends IKeyData<U1K, U1T>, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7T> //
        extends SimpleKeyPatternLikeMakeRule7<Tp, Param, K, IParameterizedKeys<?, ?>, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>
        implements IKeyPatternMakeRule7<Tp, Param, K, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T> {

    public SimpleKeyPatternMakeRule7(int priority, @NotNull Tp pattern, @NotNull CompileFunction7<T, K, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn, @NotNull U1s input1s, @NotNull U2s input2s, @NotNull U3s input3s, @NotNull U4s input4s, @NotNull U5s input5s, @NotNull U6s input6s, @NotNull U7s input7s) {
        super(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s, input7s);
    }

    public static <Tp extends IKeyPattern<Param, K>, Param, K, //
            U1s extends IParameterizedKeys<Param, U1K>, U1K, //
            U2s extends IParameterizedKeys<Param, U2K>, U2K, //
            U3s extends IParameterizedKeys<Param, U3K>, U3K, //
            U4s extends IParameterizedKeys<Param, U4K>, U4K, //
            U5s extends IParameterizedKeys<Param, U5K>, U5K, //
            U6s extends IParameterizedKeys<Param, U6K>, U6K, //
            U7s extends IParameterizedKeys<Param, U7K>, U7K, //
            T extends IKeyData<K, TT>, TT, //
            U1 extends IKeyData<U1K, U1T>, U1T
            , //
            U2 extends IKeyData<U2K, U2T>, U2T
            , //
            U3 extends IKeyData<U3K, U3T>, U3T
            , //
            U4 extends IKeyData<U4K, U4T>, U4T
            , //
            U5 extends IKeyData<U5K, U5T>, U5T
            , //
            U6 extends IKeyData<U6K, U6T>, U6T
            , //
            U7 extends IKeyData<U7K, U7T>, U7T
            > //
    Builder<Tp, Param, K, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T> builder() {
        return new Builder<>();
    }

    public static class Builder<Tp extends IKeyPattern<Param, K>, Param, K, //
            U1s extends IParameterizedKeys<Param, U1K>, U1K, //
            U2s extends IParameterizedKeys<Param, U2K>, U2K, //
            U3s extends IParameterizedKeys<Param, U3K>, U3K, //
            U4s extends IParameterizedKeys<Param, U4K>, U4K, //
            U5s extends IParameterizedKeys<Param, U5K>, U5K, //
            U6s extends IParameterizedKeys<Param, U6K>, U6K, //
            U7s extends IParameterizedKeys<Param, U7K>, U7K, //
            T extends IKeyData<K, TT>, TT, //
            U1 extends IKeyData<U1K, U1T>, U1T
            , //
            U2 extends IKeyData<U2K, U2T>, U2T
            , //
            U3 extends IKeyData<U3K, U3T>, U3T
            , //
            U4 extends IKeyData<U4K, U4T>, U4T
            , //
            U5 extends IKeyData<U5K, U5T>, U5T
            , //
            U6 extends IKeyData<U6K, U6T>, U6T
            , //
            U7 extends IKeyData<U7K, U7T>, U7T
            > //
            extends SimpleKeyPatternLikeMakeRule7.Builder<Builder<Tp, Param, K, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>, //
            Tp, Param, K, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T> {

        public SimpleKeyPatternMakeRule7<Tp, Param, K, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T> build() {
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
            if (input4s == null)
                throw new NullPointerException("input4s");
            if (input5s == null)
                throw new NullPointerException("input5s");
            if (input6s == null)
                throw new NullPointerException("input6s");
            if (input7s == null)
                throw new NullPointerException("input7s");
            return new SimpleKeyPatternMakeRule7<>(priority, pattern, fn, input1s, input2s, input3s, input4s, input5s, input6s, input7s);
        }

    }

}
