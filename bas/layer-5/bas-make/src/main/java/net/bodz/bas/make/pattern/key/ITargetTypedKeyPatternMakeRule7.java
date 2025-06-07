package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule7;

public interface ITargetTypedKeyPatternMakeRule7<Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
        U1s extends IParameterizedKey<Param, U1K>, //
        U2s extends IParameterizedKey<Param, U2K>, //
        U3s extends IParameterizedKey<Param, U3K>, //
        U4s extends IParameterizedKey<Param, U4K>, //
        U5s extends IParameterizedKey<Param, U5K>, //
        U6s extends IParameterizedKey<Param, U6K>, //
        U7s extends IParameterizedKey<Param, U7K>, //
        T extends IKeyData<TK, TT>, TT, //
        U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
        extends IKeyPatternLikeMakeRule7<Tp, Param, TK, IParameterizedKey<?, ?>, U1s, U2s, U3s, U4s, U5s, U6s, U7s, T, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>,
                ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5(), getInput6(), getInput7() };
    }

}
