package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule1;

public interface ITargetPatternMakeRule1<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends IKeyPatternLikeMakeRule1<Tp, Param, TK, IParameterizedKeys<?, ?>, Us, UK, T, TT, U, UT>,
                ITargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKeys<?, ?>[] getInputs() {
        return new IParameterizedKeys[] { getInput1() };
    }

}
