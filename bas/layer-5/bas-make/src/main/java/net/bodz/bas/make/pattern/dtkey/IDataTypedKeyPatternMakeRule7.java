package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule7;

public interface IDataTypedKeyPatternMakeRule7<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        U1s extends IDataTypedParameterizedKey<Param, U1K, U1T>, //
        U2s extends IDataTypedParameterizedKey<Param, U2K, U2T>, //
        U3s extends IDataTypedParameterizedKey<Param, U3K, U3T>, //
        U4s extends IDataTypedParameterizedKey<Param, U4K, U4T>, //
        U5s extends IDataTypedParameterizedKey<Param, U5K, U5T>, //
        U6s extends IDataTypedParameterizedKey<Param, U6K, U6T>, //
        U7s extends IDataTypedParameterizedKey<Param, U7K, U7T>, //
        T extends IKeyData<K, TT>, TT, //
        U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
        extends IKeyPatternLikeMakeRule7<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, U1s, U2s, U3s, U4s, U5s, U6s, U7s, T, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>,
                IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5(), getInput6(), getInput7() };
    }

}
