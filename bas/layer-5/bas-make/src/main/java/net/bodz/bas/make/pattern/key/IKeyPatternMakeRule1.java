package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule1;

public interface IKeyPatternMakeRule1<Tp extends IKeyPattern<Param, K>, Param, K, //
        Us extends IParameterizedKey<Param, UK>, UK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT> //
        extends IKeyPatternLikeMakeRule1<Tp, Param, K, IParameterizedKey<?, ?>, Us, UK, T, TT, U, UT>,
                IKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] { getInput1() };
    }

}
