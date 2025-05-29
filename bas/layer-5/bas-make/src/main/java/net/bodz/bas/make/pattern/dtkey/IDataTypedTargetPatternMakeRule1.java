package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule1;

public interface IDataTypedTargetPatternMakeRule1<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends IKeyPatternLikeMakeRule1<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, T, TT, U, UT>,
                IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IDataTypedParameterizedKeys<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKeys[] { getInput1() };
    }

}
