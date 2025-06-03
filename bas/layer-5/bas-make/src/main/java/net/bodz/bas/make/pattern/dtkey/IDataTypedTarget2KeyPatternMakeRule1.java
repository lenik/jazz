package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule1;

public interface IDataTypedTarget2KeyPatternMakeRule1<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends IKeyPatternLikeMakeRule1<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, T, TT, U, UT>,
                IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] { getInput1() };
    }

}
