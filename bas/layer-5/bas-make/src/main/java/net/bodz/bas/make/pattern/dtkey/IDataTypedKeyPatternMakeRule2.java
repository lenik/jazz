package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule2;

public interface IDataTypedKeyPatternMakeRule2<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
        T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT, V extends IKeyData<VK, VT>, VT>
        extends IKeyPatternLikeMakeRule2<Tp, Param, K, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, Vs, VK, T, TT, U, UT, V, VT>,
                IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IDataTypedParameterizedKeys<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKeys[] { getInput1(), getInput2() };
    }

}
