package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule7;

public interface IDataTypedKeyPatternMakeRule7<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        U1s extends IDataTypedParameterizedKeys<Param, U1K, U1T>, U1K, //
        U2s extends IDataTypedParameterizedKeys<Param, U2K, U2T>, U2K, //
        U3s extends IDataTypedParameterizedKeys<Param, U3K, U3T>, U3K, //
        U4s extends IDataTypedParameterizedKeys<Param, U4K, U4T>, U4K, //
        U5s extends IDataTypedParameterizedKeys<Param, U5K, U5T>, U5K, //
        U6s extends IDataTypedParameterizedKeys<Param, U6K, U6T>, U6K, //
        U7s extends IDataTypedParameterizedKeys<Param, U7K, U7T>, U7K, //
        T extends IKeyData<K, TT>, TT, //
        U1 extends IKeyData<U1K, U1T>, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7T> //
        extends IKeyPatternLikeMakeRule7<Tp, Param, K, IDataTypedParameterizedKeys<?, ?, ?>, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>,
                IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IDataTypedParameterizedKeys<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKeys[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5(), getInput6(), getInput7() };
    }

}
