package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule1;

public interface IDataTypedKeyPatternMakeRule1<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends IKeyPatternLikeMakeRule1<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, T, TT, U, UT>,
                IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] { getInput1() };
    }

}
