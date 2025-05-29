package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule2;

public interface IDataTypedTargetPatternMakeRule2<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT> //
        extends IKeyPatternLikeMakeRule2<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, Vs, VK, T, TT, U, UT, V, VT>,
                IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IDataTypedParameterizedKeys<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKeys[] { getInput1(), getInput2() };
    }

}
