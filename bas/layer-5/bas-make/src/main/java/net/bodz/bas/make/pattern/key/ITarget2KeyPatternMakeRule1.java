package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule1;

public interface ITarget2KeyPatternMakeRule1<Tp extends ITarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedKey<Param, UK>, UK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends IKeyPatternLikeMakeRule1<Tp, Param, TK, IParameterizedKey<?, ?>, Us, UK, T, TT, U, UT>,
                ITarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] { getInput1() };
    }

}
